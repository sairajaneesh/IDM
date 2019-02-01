package com.hyva.idm.sass.sassrespositories;

import com.hyva.idm.sass.sassentities.CustomerNotifications;
import com.hyva.idm.sass.sassentities.SassCustomer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SassCustomerNotificationsRepository extends CrudRepository<CustomerNotifications, Long> {
    //List<CustomerNotifications> findByFromRegNo(String fromRegno);
//    List<CustomerNotifications> findBySubscriptionTypeNotInAndToRegnoAndStatus(String subcriptiontype ,String toReg,String status);
    List<CustomerNotifications> findByTypeFlagOrTypeFlagAndToRegnoAndStatus(String subcriptiontype,String subscriptionSales ,String toReg,String status);
    List<CustomerNotifications> findByToRegno(String fromRegno);
    List<CustomerNotifications> findByFromRegnoAndSubscriptionTypeAndViewStatus(String fromRegno,String subscriptionType,String viewStatus);
    CustomerNotifications findByFromRegnoAndToRegnoAndTypeDoc(String typeDoc,String fromRegNo,String toRegNO);


//    List<CustomerNotifications> findByToRegnoAndStatusAndFromRegnoNot(String toReg,String status,String fromReg);

}
