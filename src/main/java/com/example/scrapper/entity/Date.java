package com.example.scrapper.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Builder
public class Date {
    private final LocalDate startDate;
    private final LocalDate endDate;
}
