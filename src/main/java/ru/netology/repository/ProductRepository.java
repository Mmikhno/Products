package ru.netology.repository;


import ru.netology.AlreadyExistException;
import ru.netology.NotFoundException;
import ru.netology.domain.Product;

public class ProductRepository {
    protected Product[] products = new Product[0];

    //сохранение
    public void save(Product newProduct) {
        Product[] temp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            temp[i] = products[i];
        }
        if (findById(newProduct.getId()) != null) {
            throw new AlreadyExistException("Element with ID " + newProduct.getId() + " already exists");
        } else {
            temp[temp.length - 1] = newProduct;
        }

        products = temp;
    }

    //получение сохраненных продуктов
    public Product[] findAll() {
        return products;
    }

    //удаление по id
    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Element with ID " + id + " is not found"
            );
        }
        Product[] temp = new Product[products.length - 1];
        int index = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                temp[index] = product;
                index++;
            }
        }
        products = temp;
    }

    public Product findById(int id) {
        Product result = null;
        for (Product product : products) {
            if (product.getId() == id) {
                result = product;
            }
        }
        return result;
    }
}
