package com.ibanfr.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Table(name = "team")
@Entity
@Getter
@ToString
public class Team extends DomainEntity implements Serializable {

    private static final long serialVersionUID = -6197675373768898713L;

    @Column
    private String name;

    /**
     * OneToMany association with {@link Employee} entity mapped as a List.
     * <p>
     * Defining {@link CascadeType#ALL} for the associated {@link Employee} entity.
     * <p>
     * Defining orphanRemoval to cascade the remove operation to entities that have been removed from the
     * relationship.
     * <p>
     * Using {@link JoinColumn} to implement unidirectional one-to-many association using a foreign key mapping.
     * <p>
     * Specifying {@link ForeignKey} name to avoid using the persistence provider's default foreign key strategy.
     *
     * @return the List of {@link Employee} entities.
     */
    @Setter
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "FK_user_team_id"))
    private List<Employee> members;

    /**
     * Empty constructor required by hibernate
     */
    protected Team(){
    }

    private Team(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public static Team of(String name) {
        return new Team(name);
    }
}