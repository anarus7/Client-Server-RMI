package ro.ubb.lab3.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Purchase implements BaseEntity<Long>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull(message = "id cannot be null")
    private Long id;
    @NotNull(message = "book id cannot be null")
    private Long bookId;
    @NotNull(message = "client id cannot be null")
    private Long clientId;
    @Positive(message = "number must pe positive")
    private int numberSold;
    @PastOrPresent(message = "date cannot be in the future")
    private LocalDate dateOfPurchase;

    public Purchase(Long id, Long bookId, Long clientId, int numberSold, LocalDate dateOfPurchase) {
        this.id = id;
        this.bookId = bookId;
        this.clientId = clientId;
        this.numberSold = numberSold;
        this.dateOfPurchase = dateOfPurchase;
    }

    public Purchase(){}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public int getNumberSold() {
        return numberSold;
    }

    public void setNumberSold(int numberSold) {
        this.numberSold = numberSold;
    }
    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }
    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", bookId='" + bookId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", number Sold='" + numberSold + '\'' +
                ", date of purchase=" + dateOfPurchase +
                '}';
    }

}
