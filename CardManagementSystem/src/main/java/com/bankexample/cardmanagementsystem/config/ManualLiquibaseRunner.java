package com.bankexample.cardmanagementsystem.config;

//import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class ManualLiquibaseRunner {

    private final DataSource dataSource;


//    @EventListener(ApplicationReadyEvent.class)
//    public void onApplicationReady() throws Exception {
//        Thread.sleep(10000); // Задержка 10 секунд
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setDataSource(dataSource);
//        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.yaml");
//        liquibase.afterPropertiesSet();
//        System.out.println("Liquibase ran manually.");
//    }
}
