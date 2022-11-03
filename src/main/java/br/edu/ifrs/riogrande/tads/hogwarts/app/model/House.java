package br.edu.ifrs.riogrande.tads.hogwarts.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "house")
public class House {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "name", length = 30, nullable = false)
    String name;

    @Column(name = "founder", length = 40, nullable = false)
    String founder;

}
