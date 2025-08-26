package com.codewitharal.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    //    @ManyToOne
    //    @JoinColumn(name = "user_id")
    //    private User user;
    @OneToMany(mappedBy = "user" , cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    //add address method
    public void addAddress(Address address) {
        this.addresses.add(address);
        address.setUser(this);
    }

    //remove address method
    public void removeAddress(Address address) {
        this.addresses.remove(address);
        address.setUser(null);
    }

    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Builder.Default
    private Set<Tag> tags= new HashSet<>();

    public void addTag(String tagName) {

        Tag newTag = new Tag(tagName);
        this.tags.add(newTag);
        newTag.getUsers().add(this);
    }

//    @OneToOne
//    @JoinColumn(name = "id")
//    private User user;
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Profile profile;

    @ManyToMany
    @JoinTable(
            name = "wishlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> wishList = new HashSet<>();

    public void addToTheWishList(Product product) {
        wishList.add(product);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "email = " + email + ")";
    }
}
