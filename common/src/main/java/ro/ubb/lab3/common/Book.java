package ro.ubb.lab3.common;

import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;

public class Book implements BaseEntity<Long>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Id cannot be null")
    private Long id;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @Size(min=10,max = 20)
    private String author;
    @NotBlank(message = "Publisher cannot be blank")
    private String publisher;
    @Min(value = 1)
    private double price;
    @Positive
    private int stock;

    public Book(Long id, String title, String author, String publisher, double price, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.stock = stock;
    }

    public Book() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
