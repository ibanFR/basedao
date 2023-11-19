package com.ibanfr.domain;

import jakarta.persistence.Entity;
import lombok.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Employee extends Identity{

    String name;
}
