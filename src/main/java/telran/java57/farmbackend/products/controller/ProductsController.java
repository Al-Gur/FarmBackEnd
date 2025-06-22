package telran.java57.farmbackend.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.java57.farmbackend.products.dto.ProductDto;
import telran.java57.farmbackend.products.service.ProductsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductsController {
    final ProductsService productsService;

    @GetMapping("showall")
    public Iterable<ProductDto> showAll() {
        return productsService.getAllProducts();
    }

    @PostMapping("product")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productsService.addProduct(productDto);
    }

    @PutMapping("product")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productsService.updateProduct(productDto);
    }

    @DeleteMapping("product/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {
        return productsService.deleteProduct(productId);
    }
}
