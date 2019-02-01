package com.hyva.idm.sass.sassrespositories;

import com.hyva.idm.sass.sassentities.SassUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SassUserRepository extends CrudRepository<SassUser, Long> {

    SassUser findByEmailAndAndUserNameAndAndPasswordUser(String email, String  userName,String Password);

    SassUser findByEmail(String Email);
    SassUser findByUserToken(String UserToken);
    SassUser findByUseraccountid(int userId);
    SassUser findByCompanyNameAndUserName(String companyName,String userName );
    List<SassUser>getByCompanyNameIs(String companyName);


    int countByCompanyName(String companyName);


//    List<SassUser>getByUseraccount_id(int Useraccount_id);
}
