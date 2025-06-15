package telran.farmbackend.products.service;

import telran.farmbackend.products.dto.ProductDto;

import java.util.List;

public interface ProductsService {
    List<ProductDto> getAllProducts();

    ProductDto addProduct(ProductDto productDto);
}
