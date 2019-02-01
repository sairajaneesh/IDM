package com.hyva.idm.sass.sassrespositories;

import com.hyva.idm.sass.sassentities.SaasState;
import com.hyva.idm.sass.sasspojo.SaasStatePojo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaasStateRepository extends CrudRepository<SaasState, Long> {
    SaasState findByStateName(String state);
}
