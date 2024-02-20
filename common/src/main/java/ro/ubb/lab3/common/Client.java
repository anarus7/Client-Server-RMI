package ro.ubb.lab3.common;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Client implements BaseEntity<Long>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @NotNull(message = "id must not be null")
    private Long id;
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @Pattern(regexp = "[0-9]{10}", message = "phone number must be 10 numbers long")
    private String phoneNumber;

    public Client() {
    }

    public Client(Long id, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;

    }

    public String stringifyObject() {
        return String.join(":", id.toString(), firstName, lastName, phoneNumber);
    }

    public static Client getObjectFromString(String clientString) {
        String[] parts = clientString.split(":");
        return new Client(Long.parseLong(parts[0]), parts[1], parts[2], parts[3]);
    }

    public static List<Client> getClientList(String clientsString) {
        String[] separatedBy = clientsString.split(";");
        return Arrays.stream(separatedBy)
                .map(Client::getObjectFromString)
                .collect(Collectors.toList());
    }

    public static String getClientsFromServerAsString(List<Client> clients) {
        List<String> clientsString = clients.stream().map(Client::stringifyObject).collect(Collectors.toList());
        return String.join(";", clientsString);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
