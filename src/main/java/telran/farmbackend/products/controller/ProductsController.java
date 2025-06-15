package telran.farmbackend.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.farmbackend.products.dto.ProductDto;
import telran.farmbackend.products.service.ProductsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductsController {
    final ProductsService productsService;

    @GetMapping("showall")
    public Iterable<ProductDto> showAll(){ return productsService.getAllProducts(); }

    @PostMapping("addproduct")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productsService.addProduct(productDto);
    }
}
