package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.model.Employee;
import com.ibanfr.domain.model.Team;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import org.jboss.weld.junit5.auto.AddEnabledInterceptors;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@EnableAutoWeld
@AddEnabledInterceptors(TransactionInterceptor.class)
class TeamPersistentRepositoryIT {

    final Map<String, Object> settings = Map.of("hibernate.connection.url",
                                                //"jdbc:mysql://localhost:3306/ivan_test?useSSL=false&createDatabaseIfNotExist=true",
                                                "jdbc:h2:mem:testdb",
                                                //"hibernate.connection.username",
                                                //"root",
                                                //"hibernate.connection.password",
                                                //"assf1r3mysql",
                                                "hibernate.connection.driver_class",
                                                //"com.mysql.cj.jdbc.Driver",
                                                "org.h2.Driver",
                                                //"hibernate.dialect",
                                                //"org.hibernate.dialect.MySQLDialect",
                                                "hibernate.hbm2ddl.auto",
                                                "update",
                                                "hibernate.show_sql",
                                                "true",
                                                "hibernate.current_session_context_class",
                                                "thread");


    @ApplicationScoped
    @Produces
    SessionFactory sessionFactory = new SessionFactoryProducer()
            .produceSessionFactory(settings,
                                   Employee.class,
                                   Team.class);


    @Inject
    TeamPersistentRepository teamRepository;

    Team team;
    Employee ivan;

    @BeforeEach
    void setUp() {

        //given
        team = Team.of("Team name");
        ivan = Employee.of("Ivan");

        team.setMembers(List.of(ivan));
    }

    @Test
    @DisplayName("should create a new instance")
    void shouldCreateANewInstance() {

        //then
        assertThat(teamRepository.sessionFactory.isOpen()).isTrue();
    }

    @Test
    @DisplayName("should save Team with Employees")
    void shouldSaveTeamWithEmployees() {

        //when
        teamRepository.save(team);

        //then
        assertThat(team.getMembers())
                .allMatch(employee -> Objects.nonNull(employee.getId()));
    }

    @Test
    @DisplayName("should find the Team and employees within the Team")
    void shouldFindTheTeamAndEmployeesWithinTheTeam() {

        //given
        teamRepository.save(team);

        //when
        teamRepository.findById(team.getId());

        //then
        assertThat(team.getMembers()).isNotEmpty()
                .hasSize(1);
    }

}