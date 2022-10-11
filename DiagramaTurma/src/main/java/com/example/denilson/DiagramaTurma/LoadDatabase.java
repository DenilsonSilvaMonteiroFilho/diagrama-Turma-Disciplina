package com.example.denilson.DiagramaTurma;

import com.example.denilson.DiagramaTurma.TurmaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TurmaRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Turma("Analise de Dados", "Marcus")));
            log.info("Preloading " + repository.save(new Turma("Calculo", "Helio")));
        };
    }
}