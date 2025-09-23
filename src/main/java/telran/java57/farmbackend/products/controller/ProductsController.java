package telran.java57.farmbackend.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.java57.farmbackend.products.dto.AddProductDto;
import telran.java57.farmbackend.products.dto.OrderDto;
import telran.java57.farmbackend.products.dto.ProductDto;
import telran.java57.farmbackend.products.dto.ProductListDto;
import telran.java57.farmbackend.products.service.ProductsService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductsController {
    final ProductsService productsService;

    @GetMapping("showall")
    public ProductListDto showAll() {
        return productsService.getAllProducts();
    }

    @GetMapping("show/category={selectedCategory}&maxprice={maxPrice}&sort={sortBy}")
    public ProductListDto show(@PathVariable String selectedCategory, @PathVariable Integer maxPrice,
                               @PathVariable String sortBy) {
        return productsService.getProducts(selectedCategory, maxPrice, sortBy);
    }

    @PostMapping("product")
    public ProductDto addProduct(Principal principal, @RequestBody AddProductDto productDto) {
        return productsService.addProduct(principal.getName(), productDto);
    }

    @PostMapping("test")
    public void test(Principal principal, @RequestBody String dto) {
        System.out.println(dto);
    }

    @PutMapping("product")
    public ProductDto updateProduct(Principal principal, @RequestBody ProductDto productDto) {
        return productsService.updateProduct(principal.getName(), productDto);
    }

    @PutMapping("reset_categories")
    public void resetCategories(Principal principal) {
        productsService.resetProductsCategories();
    }

    @PostMapping("productdelete/{productId}")
    public ProductDto deleteProduct(Principal principal, @PathVariable String productId) {
        return productsService.deleteProduct(principal.getName(), productId);
    }

    @PutMapping("preorder")
    public boolean preOrderProduct(Principal principal, @RequestBody OrderDto orderDto) {
        return productsService.preOrderProduct(principal.getName(), orderDto);
    }

    @GetMapping("cart/{login}")
    public Iterable<ProductDto> getUserCart(@PathVariable String login) {
        return productsService.getUserCart(login);
    }

}
