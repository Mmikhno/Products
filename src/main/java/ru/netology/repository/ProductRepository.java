package ru.netology.repository;

import ru.netology.domain.Product;

public class ProductRepository {
    protected Product[] products = new Product[0];

    //сохранение
    public void save(Product newProduct) {
        Product[] temp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            temp[i] = products[i];
        }
        temp[temp.length - 1] = newProduct;
        products = temp;
    }

    //получение сохраненных продуктов
    public Product[] findAll() {
        return products;
    }

    //удаление по id
    public void removeById(int id) {
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
}
