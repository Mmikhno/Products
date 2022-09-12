package ru.netology.manager;

import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;

public class ProductManager {
    protected ProductRepository repo = new ProductRepository();

    public ProductManager(ProductRepository repo) {
        this.repo = repo;
    }

    public void add(Product product) {
        repo.save(product);
    }

    public void removeById(int id) {
        repo.removeById(id);
    }

    public Product[] findAll() {
        return repo.findAll();
    }

    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product product : repo.findAll()) {
            if (matches(product, text)) {
                Product[] temp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    temp[i] = result[i];
                }
                temp[temp.length - 1] = product;
                result = temp;
            }
        }
        return result;
    }

   /* public Product[] searchBy2(String text) {
        Product[] result = new Product[0];
        ProductRepository temp = new ProductRepository();
        for (Product product : repo.findAll()) {
            if (matches(product, text)) {
                temp.save(product);
            }
            result = temp.findAll();
        }
        return result;
    }*/
}