package telran.java57.farmbackend.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.java57.farmbackend.products.dao.ProductsRepository;
import telran.java57.farmbackend.products.dto.ProductDto;
import telran.java57.farmbackend.products.model.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    final ProductsRepository productsRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productsRepository.findAll().stream()
                .map(ProductDto::new).toList();
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = new Product(productDto.getName(), productDto.getQuantity(), productDto.getProducer());
        return new ProductDto(productsRepository.save(product));
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product productOld = productsRepository.findById(productDto.getId())
                .orElseThrow(RuntimeException::new);
        Product productNew = new Product(productDto.getId(), productDto.getName(), productDto.getQuantity(),
                productDto.getProducer());
        productsRepository.save(productNew);
        return new ProductDto(productOld);
    }

    @Override
    public ProductDto deleteProduct(String productId) {
        Product product = productsRepository.findById(productId)
                        .orElseThrow(RuntimeException::new);
        productsRepository.deleteById(productId);
        return new ProductDto(product);
    }
}
