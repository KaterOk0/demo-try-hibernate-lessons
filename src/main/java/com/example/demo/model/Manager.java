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
@Table(name = "manager")
@DiscriminatorValue("MANGER")
public class Manager extends Employee {
    @Column(name = "idle_hours")
    private int numberOfIdleHours;
}
