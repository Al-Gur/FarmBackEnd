package telran.java57.farmbackend.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.java57.farmbackend.accounting.dao.UserAccountRepository;
import telran.java57.farmbackend.accounting.dto.exceptions.UserNotFoundException;
import telran.java57.farmbackend.accounting.model.UserAccount;
import telran.java57.farmbackend.products.dao.CategoriesRepository;
import telran.java57.farmbackend.products.dao.ProductsRepository;
import telran.java57.farmbackend.products.dto.*;
import telran.java57.farmbackend.products.model.Product;
import telran.java57.farmbackend.products.model.ProductCategory;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    final ProductsRepository productsRepository;
    final CategoriesRepository categoriesRepository;
    final UserAccountRepository userAccountRepository;

    private ProductDto newProductDto(Product product) {
        String producerLogin = product.getProducer();
        Optional<UserAccount> producer = userAccountRepository.findById(producerLogin);
        String producerFullName = producer.isPresent() ? producer.get().getFullName() : "[" + producerLogin + "]";
        return new ProductDto(product.getId(), product.getName(), product.getImage(), product.getCategory(),
                product.getPrice(), product.getQuantity(), producerFullName);
    }

    private void addToCategory(String category) {
        ProductCategory productCategory = categoriesRepository.findById(category)
                .orElseGet(() -> new ProductCategory(category, 0));
        productCategory.setCount(productCategory.getCount() + 1);
        categoriesRepository.save(productCategory);
    }

    private void removeFromCategory(String category) {
        Optional<ProductCategory> existingCategory = categoriesRepository.findById(category);
        if (existingCategory.isPresent()) {
            ProductCategory productCategory = existingCategory.get();
            productCategory.setCount(productCategory.getCount() - 1);
            categoriesRepository.save(productCategory);
        }
    }


    public Iterable<ProductCategoryDto> getProductCategories() {
        ArrayList<ProductCategoryDto> productCategories = new ArrayList<>(
                categoriesRepository.findAll().stream()
                        .map(category ->
                                new ProductCategoryDto(category.getCategory(), category.getCount()))
                        .toList());
        productCategories.add(0, new ProductCategoryDto("", (int) productsRepository.count()));
        return productCategories;
    }

    public Iterable<ProductCategoryDto> getEmptyProductCategories() {
        return categoriesRepository.findAll().stream()
                .filter(category -> category.getCount() <= 0)
                .map(category ->
                        new ProductCategoryDto(category.getCategory(), category.getCount()))
                .toList();
    }


    @Override
    public ProductListDto getAllProducts() {
        return new ProductListDto(
                productsRepository.findAll().stream().map(this::newProductDto).toList(),
                getProductCategories()
        );
    }

    @Override
    public ProductListDto getProducts(String selectedCategory, Integer maxPrice, String sortBy) {
        maxPrice = maxPrice > 0 ? maxPrice : Integer.MAX_VALUE;
        Stream<Product> productStream = selectedCategory.isEmpty() ?
                productsRepository.findProductsByPriceBefore(maxPrice)
                : productsRepository.findProductsByCategoryAndPriceBefore(selectedCategory, maxPrice);
        if (!sortBy.isEmpty()) {
            productStream = productStream.sorted((p1, p2) -> switch (sortBy) {
                case "Name" -> p1.getName().compareToIgnoreCase(p2.getName());
                case "Price" -> p1.getPrice() - p2.getPrice();
                case "Category" -> p1.getCategory().compareToIgnoreCase(p2.getCategory());
                case "Producer" -> p1.getProducer().compareToIgnoreCase(p2.getProducer());
                default -> 1;
            });
        }
        return new ProductListDto(productStream.map(this::newProductDto).toList(), getProductCategories());
    }

    @Override
    public ProductDto addProduct(String username, AddProductDto productDto) {
        Product product = new Product(productDto.getName(), productDto.getImage(),
                productDto.getCategory(), productDto.getPrice(), productDto.getQuantity(), username);
        product = productsRepository.save(product);
        addToCategory(product.getCategory());
        return newProductDto(product);
    }

    @Override
    public ProductDto updateProduct(String username, ProductDto productDto) {
        Product productOld = productsRepository.findById(productDto.getId())
                .orElseThrow(RuntimeException::new);
        Product productNew = new Product(productDto.getId(), productDto.getName(), productDto.getImage(),
                productDto.getCategory(), productDto.getPrice(), productDto.getQuantity(), productDto.getProducer());
        if (!(productDto.getProducer().equals(username) && productOld.getProducer().equals(username))) {
            throw new SecurityException();
        }
        productNew = productsRepository.save(productNew);
        if (!productNew.getCategory().equals(productOld.getCategory())) {
            removeFromCategory(productOld.getCategory());
            addToCategory(productNew.getCategory());
        }
        return newProductDto(productOld);
    }

    @Override
    public ProductDto deleteProduct(String username, String productId) {
        Product product = productsRepository.findById(productId)
                .orElseThrow(RuntimeException::new);
        if (!username.equals(product.getProducer())) {
            UserAccount producer = userAccountRepository.findById(username)
                    .orElseThrow(RuntimeException::new);
            if (!producer.getRoles().contains("ADMINISTRATOR")) {
                throw new SecurityException();
            }
        }
        productsRepository.deleteById(productId);
        removeFromCategory(product.getCategory());
        return newProductDto(product);
    }


    public void resetProductsCategories() {
        categoriesRepository.deleteAll();
        productsRepository.findAll().forEach(product -> addToCategory(product.getCategory()));
    }


    @Override
    public boolean preOrderProduct(String userName, OrderDto orderDto) {
        Product product = productsRepository.findById(orderDto.getProductId())
                .orElseThrow(RuntimeException::new);
        if (orderDto.getQuantity() <= product.getQuantity()) {
            UserAccount buyer = userAccountRepository.findById(userName)
                    .orElseThrow(RuntimeException::new);
            buyer.getOrders().add(orderDto);

            userAccountRepository.save(buyer);
            //productsRepository.save(product);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterable<ProductDto> getUserCart(String login) {
        UserAccount user = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        return user.getOrders().stream()
                .map(order -> {
                    Product product = productsRepository.findById(order.getProductId())
                            .orElseThrow(RuntimeException::new);
                    product.setQuantity(order.getQuantity());
                    return product;
                })
                .map(this::newProductDto).toList();
    }
}
