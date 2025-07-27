package telran.java57.farmbackend.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.java57.farmbackend.accounting.dao.UserAccountRepository;
import telran.java57.farmbackend.accounting.model.UserAccount;
import telran.java57.farmbackend.products.dao.ProductsRepository;
import telran.java57.farmbackend.products.dto.AddProductDto;
import telran.java57.farmbackend.products.dto.ProductDto;
import telran.java57.farmbackend.products.model.Product;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    final ProductsRepository productsRepository;
    final UserAccountRepository userAccountRepository;

    private ProductDto newProductDto(Product product) {
        String producerLogin = product.getProducer();
        Optional<UserAccount> producer = userAccountRepository.findById(producerLogin);
        String producerFullName = producer.isPresent() ? producer.get().getFullName() : "[" + producerLogin + "]";
        return new ProductDto(product.getId(), product.getName(), product.getImage(), product.getCategory(),
                product.getQuantity(), producerFullName);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productsRepository.findAll().stream().map(this::newProductDto).toList();
    }

    @Override
    public ProductDto addProduct(String username, AddProductDto productDto) {
        Product product = new Product(productDto.getName(), productDto.getImage(), productDto.getCategory(),
                productDto.getQuantity(), username);
        return newProductDto(productsRepository.save(product));
    }

    @Override
    public ProductDto updateProduct(String username, ProductDto productDto) {
        Product productOld = productsRepository.findById(productDto.getId())
                .orElseThrow(RuntimeException::new);
        Product productNew = new Product(productDto.getId(), productDto.getName(), productDto.getImage(),
                productDto.getCategory(), productDto.getQuantity(), productDto.getProducer());
        if (!(productDto.getProducer().equals(username)) && productOld.getProducer().equals(username)) {
            throw new SecurityException();
        }
        productsRepository.save(productNew);
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
        return newProductDto(product);
    }
}
