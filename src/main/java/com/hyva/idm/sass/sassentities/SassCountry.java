package com.hyva.idm.sass.sassentities;

import com.hyva.idm.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class SassCountry extends BaseEntity<String> {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    private Long countryId;
    private String countryName;
    private String flag;
    private String locationId;
    private String useraccount_id;
    private String status;

    public SassCountry(Long countryId) {
        this.countryId=countryId;
    }

    public SassCountry() {
    }

    public SassCountry(String countryName, String flag, String status) {
        this.countryName = countryName;
        this.flag = flag;
        this.status = status;
    }
}
