package com.example.scrapper;

import com.example.scrapper.service.Scrapper;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class runner implements ApplicationRunner {

    private final Scrapper scrapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        scrapper.get();
    }
}
