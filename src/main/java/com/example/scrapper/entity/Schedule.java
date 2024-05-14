package com.example.scrapper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String schedule;

    public List<LocalDate> calculateBetweenDate(){
        List<LocalDate> dates = new ArrayList<>();
        LocalDate start = this.startDate;

        while (!start.equals(this.endDate)){
            dates.add(start);
            start = start.plusDays(1);
        }

        dates.add(endDate);

        return dates;
    }
}
