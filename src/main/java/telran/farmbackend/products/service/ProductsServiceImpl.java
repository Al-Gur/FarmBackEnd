package telran.farmbackend.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.farmbackend.products.dao.ProductsRepository;
import telran.farmbackend.products.dto.ProductDto;
import telran.farmbackend.products.model.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService{
    final ProductsRepository productsRepository;


    @Override
    public List<ProductDto> getAllProducts() {
        return productsRepository.findAll().stream()
                .map(product -> new ProductDto(product))
                .toList();
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return new ProductDto(productsRepository.save(new Product(productDto)));
    }
}
