package cp.webapp.web.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(of = {"username"})
@Entity
public class User {

    @Id
    private String username;
    private String password;
    private String firstname;
    private String lastname; // aggiungere email!!! + subscribe
    private String country;
    private String street;
    private String houseNumber;
    private String towncity;
    private String zip;
    private String phone;

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, Role role, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /* public User(String username, String country, String street, String houseNumber, String towncity, String zip, String phone ) {
        this.username = username;
        this.country = country;
        this.street = street;
        this.houseNumber = houseNumber;
        this.towncity = towncity;
        this.zip = zip;
        this.phone = phone;

    }*/


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Role role;

    @OneToMany
    private List<Item> favorites = new ArrayList<>();

    @OneToMany
    private List<CustomOrder> orders = new ArrayList<>();

    @OneToMany
    private List<Item> cart = new ArrayList<>();


    public boolean isSeller(){
        if (this.getRole() != null)
            return this.getRole().getName().equals("ROLE_SELLER");
        return false;
    }

    public boolean isBuyer(){
        if (this.getRole() != null)
            return this.getRole().getName().equals("ROLE_BUYER");
        return false;
    }

    public boolean isAdmin(){
        if (this.getRole() != null)
            return this.getRole().getName().equals("ROLE_ADMIN");
        return false;
    }
    public boolean addOrder(CustomOrder order) {
        List<Item> itemsBlockedTobeSold = new ArrayList<>();
        for (Item i : cart) {
            if (i.isAvailable()) {
                i.setAvailable(false);
                itemsBlockedTobeSold.add(i);
            }
        }
        return !itemsBlockedTobeSold.isEmpty();
    }

}