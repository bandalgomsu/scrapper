package com.example.scrapper.service;

import com.example.scrapper.entity.Date;
import com.example.scrapper.entity.Schedule;
import com.example.scrapper.repository.ScheduleRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class Scrapper {
    private static final String URL = "https://www.uos.ac.kr/korCalendarYear/list.do?list_id=CA1&year_code=2024&menuid=2000003003001000000";
    private static final String CLASS = "listType02 mt30";
    private static final String TAG = "tr";

    private final ScheduleRepository scheduleRepository;

    public void get() throws IOException {
        Document document = Jsoup.connect(URL).get();
        Element body = document.body();
        Elements result = body.getElementsByClass(CLASS).select(TAG);

        result.forEach(
                html -> {
                    String year = html.id();
                    String date = html.select("td").select(".th").text();
                    String schedule = html.select("td").select(".al").select("span").text();
                    if (!date.isBlank()){
                        log.info("asdasd {} {}",date,year);
                        scheduleRepository.save(createSchedule(date, schedule,year));
                    }
                }
        );
    }

    public Schedule createSchedule(String date , String schedule, String year) {
        Date result = parseDate(date,parseYear(year));

        return Schedule.builder()
                .schedule(schedule)
                .startDate(result.getStartDate())
                .endDate(result.getEndDate())
                .build();
    }

    private String parseYear(String year){
        String pattern = "\\d{4}";

        // 정규 표현식에 해당하는 부분 추출
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(year);

        // 첫 번째 매칭된 부분 반환
        if (m.find()) {
            return m.group(0);
        }

        // 매칭되는 부분이 없을 경우 빈 문자열 반환
        return "";
    }

    private Date parseDate(String date,String year) {
        String dateRange = date.replace("[", "").replace("]", "");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String[] dateParts = dateRange.split(" ~ ");

        LocalDate startDate = LocalDate.parse(year+"-"+dateParts[0], formatter);
        LocalDate endDate = LocalDate.parse(year+"-"+dateParts[1], formatter);

        if (startDate.getMonthValue() > endDate.getMonthValue()) {
            startDate = startDate.minusYears(1);
        }

        return Date.builder().startDate(startDate).endDate(endDate).build();

    }

}
