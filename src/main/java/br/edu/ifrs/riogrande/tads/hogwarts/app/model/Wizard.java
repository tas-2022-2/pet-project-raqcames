package br.edu.ifrs.riogrande.tads.hogwarts.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "wizard")
public class Wizard {

    @Id
    @GeneratedValue
    Integer id;

    @Column(name = "name", length = 30, nullable = false)
    String name;

    @Column(name = "age", nullable = false)
    Integer age;

    @ManyToOne
    @JoinColumn(name = "house", nullable = false)
    House house;

    @ManyToMany
    @JoinTable(name = "registration", joinColumns = @JoinColumn(name = "wizard"), inverseJoinColumns = @JoinColumn(name = "class"))
    List<Class> classes;

}
