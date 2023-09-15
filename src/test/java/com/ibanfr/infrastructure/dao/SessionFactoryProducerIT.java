package com.ibanfr.infrastructure.dao;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionFactoryProducerIT {

    SessionFactoryProducer sessionFactoryProducer;

    @BeforeEach
    void setUp() {
        sessionFactoryProducer = new SessionFactoryProducer();
    }

    @Test
    @DisplayName("should produce a valid SessionFactory")
    void should_produce_a_valid_SessionFactory() {
        //given
        SessionFactory sessionFactory;
        //when
        SessionFactory factory = sessionFactoryProducer.produceH2SessionFactory();
        //then
        assertThat(factory.isOpen()).isTrue();
    }
}