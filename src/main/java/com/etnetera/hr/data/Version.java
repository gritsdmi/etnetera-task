package com.etnetera.hr.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Version extends AbstractEntity {

    @NotNull
    private String version;
    @Temporal(TemporalType.DATE)
    private Date establishedDate;

    public Version(String version) {
        this.version = version;
        this.establishedDate = new Date();
    }
}

