package telran.java57.farmbackend.products.service;

import telran.java57.farmbackend.products.dto.AddProductDto;
import telran.java57.farmbackend.products.dto.OrderDto;
import telran.java57.farmbackend.products.dto.ProductDto;
import telran.java57.farmbackend.products.dto.ProductListDto;

public interface ProductsService {
    ProductListDto getAllProducts();

    ProductListDto getProducts(String selectedCategory, Integer maxPrice, String sortBy);

    ProductDto addProduct(String username, AddProductDto productDto);

    ProductDto updateProduct(String username, ProductDto productDto);

    ProductDto deleteProduct(String username, String productId);

    public void resetProductsCategories();

    boolean preOrderProduct(String userName, OrderDto orderDto);

    Iterable<ProductDto> getUserCart(String login);
}
