package com.hyva.idm.sass.sassservice;
import com.hyva.idm.sass.sassentities.SassUser;
import com.hyva.idm.sass.sassmapper.SassUserMapper;
import com.hyva.idm.sass.sasspojo.SassUserPojo;
import com.hyva.idm.sass.sassrespositories.SassUserRepository;
import com.hyva.idm.sass.sassutil.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class SassUserService {

    @Autowired
    SassUserRepository sassUserRepository;

    public SassUser userValidate(SassUserPojo sassUserPojo) {
        SassUser sassUser = sassUserRepository.findByEmailAndAndUserNameAndAndPasswordUser(
                sassUserPojo.getEmail(), sassUserPojo.getUserName(), sassUserPojo.getPasswordUser());
        if (sassUser!=null) {
            return sassUser;
        } else {
            return null;
        }
    }

    public SassUser saveUserDetails(SassUserPojo sassUserPojo) {
        SassUser sassUser = null;
        sassUser = sassUserRepository.findByEmail(sassUserPojo.getEmail());
        if (sassUser != null) {
            sassUser = null;
        } else {
            sassUser = SassUserMapper.mapPojoToEntity(sassUserPojo);
            sassUserRepository.save(sassUser);
        }
        return sassUser;
    }


    public List<SassUserPojo> sassUserList() {
        List<SassUser> sassUsers = (List<SassUser>) sassUserRepository.findAll();
        List<SassUserPojo> sassUserPojoList = ObjectMapperUtils.mapAll(sassUsers, SassUserPojo.class);
        return sassUserPojoList;
    }
}
