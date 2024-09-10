package com.ibanfr.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
}
