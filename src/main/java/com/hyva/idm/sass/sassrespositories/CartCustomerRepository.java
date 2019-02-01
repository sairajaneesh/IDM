package com.hyva.idm.sass.sassrespositories;

import com.hyva.idm.sass.sassentities.CartCustomerLink;
import com.hyva.idm.sass.sassentities.SassCustomer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartCustomerRepository extends CrudRepository<CartCustomerLink, Long> {


}
