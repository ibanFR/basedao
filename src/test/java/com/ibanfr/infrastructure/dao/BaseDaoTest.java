package com.ibanfr.infrastructure.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BaseDaoTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    SessionFactory sessionFactory;

    @InjectMocks
    BaseDao<FooEntity> baseDao;


    @Test
    @DisplayName("should save foo entity")
    void shouldSaveFooEntity() {
        //given
        FooEntity foo = new FooEntity();

        //when
        baseDao.save(foo);

        //then
        then(sessionFactory.getCurrentSession()).should()
                                                .save(foo);
    }

    @Nested
    @DisplayName("Find by Id")
    class FindById {

        @Test
        @DisplayName("should find entity by the given id")
        void shouldFindEntityByTheGivenId() {

            //given
            Long id = 1L;

            //when
            baseDao.findById(id);

            //then
            then(sessionFactory.getCurrentSession())
                    .should()
                    .find(any(),eq(id));
        }

    }
}
