package telran.farmbackend.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.farmbackend.products.dao.ProductsRepository;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService{
    final ProductsRepository productsRepository;
}
