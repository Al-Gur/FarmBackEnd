package telran.java57.farmbackend.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.java57.farmbackend.products.dto.ProductDto;
import telran.java57.farmbackend.products.service.ProductsService;

import java.security.Principal;

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
    public ProductDto addProduct(Principal principal, @RequestBody ProductDto productDto) {
        return productsService.addProduct(principal.getName(), productDto);
    }

    @PutMapping("product")
    public ProductDto updateProduct(Principal principal, @RequestBody ProductDto productDto) {
        return productsService.updateProduct(principal.getName(), productDto);
    }

    @DeleteMapping("product/{productId}")
    public ProductDto deleteProduct(Principal principal, @PathVariable String productId) {
        return productsService.deleteProduct(principal.getName(), productId);
    }
}
