package com.hyva.idm.sass.sassrespositories;

import com.hyva.idm.sass.sassentities.SassCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SassCurrencyRepository extends CrudRepository<SassCurrency, Long> {
    SassCurrency findByCurrencyName(String currency);

}
