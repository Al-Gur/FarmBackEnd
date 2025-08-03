package telran.java57.farmbackend.products.service;

import telran.java57.farmbackend.products.dto.AddProductDto;
import telran.java57.farmbackend.products.dto.OrderDto;
import telran.java57.farmbackend.products.dto.ProductDto;

import java.util.List;

public interface ProductsService {
    List<ProductDto> getAllProducts();

    ProductDto addProduct(String username, AddProductDto productDto);

    ProductDto updateProduct(String username, ProductDto productDto);

    ProductDto deleteProduct(String username, String productId);

    boolean preOrderProduct(String userName, OrderDto orderDto);
}
