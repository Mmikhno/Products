package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.AlreadyExistException;
import ru.netology.NotFoundException;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;


public class ProductRepositoryTest {
    ProductRepository repo = new ProductRepository();

    Product prod1 = new Product(1, "Product1", 100);
    Product prod2 = new Product(2, "Product2", 200);
    Book book3 = new Book(3, "Book3", 101, "Author1");
    Book book4 = new Book(4, "Book4", 102, "Author2");
    Smartphone smart5 = new Smartphone(5, "Smartphone5", 10_000, "Producer1");
    Smartphone smart6 = new Smartphone(6, "Smartphone6", 15_000, "Producer2");

    //добавление элементов
    @Test
    public void shouldSaveNewProduct() {
        repo.save(prod1);
        repo.save(prod2);
        repo.save(book3);
        repo.save(book4);
        repo.save(smart5);

        Product[] expected = {prod1, prod2, book3, book4, smart5};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    //удаление элемента по Id
    @Test
    public void shouldRemoveById() {
        repo.save(prod1);
        repo.save(book3);
        repo.save(smart5);
        repo.save(smart6);
        repo.removeById(5);
        Product[] expected = {prod1, book3, smart6};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    //Генерация отчета NotFoundException
    @Test
    public void shouldRemoveNonexistentId() {
        repo.save(book3);
        repo.save(book4);
        repo.save(smart5);
        repo.save(smart6);
        Assertions.assertThrows(NotFoundException.class, () -> repo.removeById(25));
    }

    //Генерация AlreadyExistsException при попытке добавления дубликата
    @Test
    public void shouldSaveDuplicatedElements() {
        repo.save(book3);
        repo.save(book4);
        repo.save(smart5);

        Assertions.assertThrows(AlreadyExistException.class, () -> repo.save(smart5));
    }

}
