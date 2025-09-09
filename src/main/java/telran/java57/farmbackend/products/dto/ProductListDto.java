package telran.java57.farmbackend.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDto {
    Iterable<ProductDto> products;
    Iterable<ProductCategoryDto> categories;
}
