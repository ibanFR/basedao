package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.model.DomainEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
class FooEntity extends DomainEntity {

    @Column
    String fooName;

}