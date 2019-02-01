package com.hyva.idm.sass.sassrespositories;

import com.hyva.idm.sass.sassentities.SassOrders;
import com.hyva.idm.sass.sassentities.SassUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SassOrdersRepository  extends CrudRepository<SassOrders, Long> {
//    public SassOrders findByOrdersName(String newsletter);
        SassOrders getSassOrdersByLicenceKey(String LicenceKey);
        List<SassOrders> findByCompanyEmail(String Email);
        List<SassOrders> findByCompanyNameIsStartingWith(String CompanyName);
        SassOrders findByLoginUniqueId(String loginUniqueId);
        SassOrders getSassOrdersByLoginUniqueId(String loginUniqueId);
        SassOrders getByPasswordIn(String password);
        List<SassOrders> findBySassUserId(SassUser userId);

        }