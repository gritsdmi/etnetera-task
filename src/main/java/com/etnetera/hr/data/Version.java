package com.etnetera.hr.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
//@NoArgsConstructor
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String version;
    private LocalDateTime establishedDate;

    public Version(String version) {
        this.version = version;
        this.establishedDate = LocalDateTime.now();
    }
}

