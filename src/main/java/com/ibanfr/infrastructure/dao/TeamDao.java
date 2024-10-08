package com.ibanfr.infrastructure.dao;

import com.ibanfr.domain.model.Team;
import com.ibanfr.domain.model.TeamRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.hibernate.SessionFactory;

@Dependent
public class TeamDao extends BaseDao<Team> implements TeamRepository {

    @Inject
    public TeamDao(SessionFactory sessionFactory) {
        super(Team.class, sessionFactory);
    }
}
