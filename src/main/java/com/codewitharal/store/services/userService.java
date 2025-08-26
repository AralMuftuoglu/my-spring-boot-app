package com.codewitharal.store.services;

import com.codewitharal.store.entities.*;
import com.codewitharal.store.repositories.*;
import com.codewitharal.store.repositories.specifications.ProductSpecification;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class userService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Transactional
    public void showEntityStates(){

        var user = User.builder()
                .name("admin")
                .password("admin")
                .email("admin@gmail.com")
                .build();

        if(entityManager.contains(user)){
            System.out.println("Persistent");
        }
        else {
            System.out.println("Transient or Detached");
        }

        userRepository.save(user);

        if(entityManager.contains(user)){
            System.out.println("Persistent");
        }
        else {
            System.out.println("Transient or Detached");
        }
    }

    @Transactional
    public void showRelatedEntities(){
        Profile userWithIdTwo = profileRepository.findById(2L).orElseThrow();
        System.out.println(userWithIdTwo.getUser().getName());
    }



    public void fetchAddress(){
        var address =  addressRepository.findById(1L).orElseThrow();
    }

    public void persistRelated(){
        var user = User.builder()
                .name("ali")
                .email("ali@gmail.com")
                .password("ali")
                .build();

        var address =  Address.builder()
                .street("ali")
                .city("ali")
                .state("ali")
                .zip("ali")
                .build();

        user.addAddress(address);

        userRepository.save(user);

    }

    @Transactional
    public void deleteRelated(){
        var user = userRepository.findById(3L).orElseThrow();
        var address = user.getAddresses().getFirst();
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void manageProducts() {
        productRepository.deleteById(2L);
    }


    //whenever there is an update in query make it transactional
    @Transactional
    public void updateProductPrices(){
        productRepository.updatePriceByCategory(BigDecimal.valueOf(32),(byte)1);
    }

    @Transactional
    public void fetchProducts(){

        var product = new Product();
        product.setName("product");

        var matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product,matcher);

        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUsers(){
        var users = userRepository.findAllWithAddresses();
        users.forEach(u-> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
    }

    @Transactional
    public void printLoyaltyPoints(){
        var users = userRepository.findLoyalUsers(2);
        users.forEach(p -> System.out.println(p.getId()+ ": "+p.getEmail()));
    }

    public void fetchProductsByCriteria(){
        var products = productRepository.findProductsByCriteria("prod", BigDecimal.valueOf(5),null);
        products.forEach(System.out::println);
    }


    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice){
        Specification<Product> specification = Specification.allOf(); // start with empty AND

        if (name != null) {
            specification = specification.and(ProductSpecification.hasName(name));
        }
        if (minPrice != null) {
            specification = specification.and(ProductSpecification.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecification.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(specification).forEach(System.out::println);
    }


    public void fetchSortedProducts(){
        var sort = Sort.by("name").and(Sort.by("price")).descending();

        productRepository.findAll(sort).forEach(System.out::println);
    }

    public void fetchPaginatedProducts(int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Product> page = productRepository.findAll(pageRequest);

        page.getContent().forEach(System.out::println);

        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();
        System.out.println("Total elements: " + totalElements);
        System.out.println("Total pages: " + totalPages);
    }

}
