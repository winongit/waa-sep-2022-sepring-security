package edu.miu.springsecurity.service;

import edu.miu.springsecurity.entity.Product;

import java.util.List;

public interface ProductService {
    void save(Product p);

    void delete(int id);

    Product getById(int id);

    List<Product> getAll();
}
