package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.ProductRepository;

public class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Book book1 = new Book(1, "Book1", 200, "Author1");
    Book book2 = new Book(2, "Book2", 250, "Author2");
    Smartphone smart3 = new Smartphone(3, "Smartphone3", 10_000, "producer1");
    Smartphone smart4 = new Smartphone(4, "Smartphone4", 15_000, "Producer2");
    Smartphone smart5 = new Smartphone(4, "Smartphone5", 15_000, "Producer3");

    @Test
    public void shouldAddNewProduct() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smart3);
        manager.add(smart4);
        Product[] expected = {book1, book2, smart3, smart4};
        Product[] actual = manager.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByText() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smart3);
        manager.add(smart4);


        Product[] expected = {book1, book2};
        Product[] actual = manager.searchBy("Boo");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenResultIsEmpty() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smart3);
        manager.add(smart4);

        Product[] expected = {};
        Product[] actual = manager.searchBy("Pen");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenSingleResult() {
        manager.add(book1);
        manager.add(smart3);
        manager.add(smart4);
        manager.add(smart5);
        Product[] expected = {book1};
        Product[] actual = manager.searchBy("1");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWhenInitialArrayIsEmpty() {
        //manager.add(book1);
        //manager.removeById(1);
        Product[] expected = {};
        Product[] actual = manager.searchBy("Book");
        Assertions.assertArrayEquals(expected, actual);
    }
}
