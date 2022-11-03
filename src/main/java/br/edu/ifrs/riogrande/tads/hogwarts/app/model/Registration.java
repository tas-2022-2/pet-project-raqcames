package br.edu.ifrs.riogrande.tads.hogwarts.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue
    Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('APPROVED', 'FAILED', 'IN_PROGRESS')", nullable = false)
    Status status;

    @ManyToOne
    @JoinColumn(name = "wizard", nullable = false)
    Wizard wizard;

    @ManyToOne
    @JoinColumn(name = "class", nullable = false)
    Class c;
}
