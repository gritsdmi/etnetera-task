package com.etnetera.hr.data;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
public class JavaScriptFramework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Version> version;

    @Column
    private LocalDateTime deprecationDate;

    @Column
    @Enumerated(EnumType.STRING)
    private HypeLevel hypeLevel; //todo change to number

    public JavaScriptFramework(String name) {
        this.name = name;
        this.hypeLevel = HypeLevel.LOW;
    }

}
