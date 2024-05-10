package com.example.scrapper;

import com.example.scrapper.repository.ScheduleRepository;
import com.example.scrapper.service.Scrapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ScrapperApplication {



    public static void main(String[] args) throws IOException {
        SpringApplication.run(ScrapperApplication.class, args);


    }

}
