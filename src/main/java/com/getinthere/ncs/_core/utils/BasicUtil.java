package com.getinthere.ncs._core.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.getinthere.ncs.course.application.domain.enums.CourseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicUtil {
    public static CourseStatus courseStatusUpdate(LocalDate startDate, LocalDate endDate) {
        LocalDate today = LocalDate.now();
        if (endDate.isBefore(today)) {
            log.info("Updating course status - FINISHED");
            return CourseStatus.FINISHED;
        } else if (!startDate.isAfter(today) && !endDate.isBefore(today)) {
            log.info("Updating course status - RUNNING");
            return CourseStatus.RUNNING;
        } else {
            log.info("Updating course status - NOT_STARTED");
            return CourseStatus.NOT_STARTED;
        }
    }

    public static List<String> parseMultiline(String raw) {
        if (raw == null || raw.trim().isEmpty())
            return Collections.emptyList();

        return Arrays.stream(raw.split("\\r?\\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .filter(s -> !s.isBlank())
                .toList();
    }

    public static List<String> parseMultilineWithoutHyphen(String raw) {
        if (raw == null || raw.trim().isEmpty())
            return Collections.emptyList();

        return Arrays.stream(raw.split("\\r?\\n"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .filter(s -> !s.isBlank())
                .map(s -> s.replaceFirst("^-\\s*", "")) // ← 하이픈+공백 제거
                .toList();
    }

    public static double scaleTo100(double score, double currentMaxScore) {
        if (currentMaxScore <= 0) {
            throw new IllegalArgumentException("현재 만점은 0보다 커야 합니다.");
        }
        double result = (score / currentMaxScore) * 100;
        return Math.round(result * 10) / 10.0;
    }

    public static String extractFirstLine(String text) {
        if (text == null || text.isBlank())
            return "";

        int newlineIndex = text.indexOf('\n');
        if (newlineIndex == -1) {
            return text.trim(); // 줄바꿈이 없는 경우 전체 리턴
        }
        return text.substring(0, newlineIndex).trim(); // 첫 줄만 추출
    }

    public static String generateAuthCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 100000 ~ 999999
        return String.valueOf(code);
    }
}
