package com.ibanfr.domain.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Builder
@Entity
@Getter
@EqualsAndHashCode
public class Employee extends DomainEntity implements Serializable {

    String name;

    protected Employee(){

    }

    private Employee(String name) {
        this.name = name;
    }

    public static Employee of(String name) {
        return new Employee(name);
    }
}
