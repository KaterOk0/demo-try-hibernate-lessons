package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "skills")
@Entity
@ToString(exclude = "employee")
public class Skill extends BaseEntity {

    @Column(name = "skillName")
    private String skillName;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public Skill(String skillName) {
        this.skillName = skillName;
    }

    public Skill() {
    }
}
