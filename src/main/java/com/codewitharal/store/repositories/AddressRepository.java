package com.codewitharal.store.repositories;

import com.codewitharal.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}