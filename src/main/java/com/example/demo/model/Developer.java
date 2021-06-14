package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "developer")
@DiscriminatorValue("DEVELOPER")
public class Developer extends Employee {

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;
}
