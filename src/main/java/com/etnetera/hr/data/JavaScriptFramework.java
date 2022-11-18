package com.etnetera.hr.data;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 *
 * @author Etnetera
 */
@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "framework")
public class JavaScriptFramework extends AbstractEntity {

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Version> version;

    @Temporal(TemporalType.DATE)
    private Date deprecationDate;

    @Column
    @Enumerated(EnumType.STRING)
    private HypeLevel hypeLevel; //todo change to number

    public JavaScriptFramework(String name) {
        this.name = name;
        this.hypeLevel = HypeLevel.LOW;
    }

}
