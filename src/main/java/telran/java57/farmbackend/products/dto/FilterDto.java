package telran.java57.farmbackend.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto {
    String selectedCategory;
    Double maxPrice;
    String sortBy;
}
