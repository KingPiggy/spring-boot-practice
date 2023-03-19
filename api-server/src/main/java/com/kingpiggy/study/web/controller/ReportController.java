package com.kingpiggy.study.web.controller;

import com.kingpiggy.study.service.ReportService;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/report")
@RestController
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<InputStreamResource> getMoviesToExcel(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startedAt,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endedAt) {
        try {
            ByteArrayInputStream result = reportService.getMoviesToExcel(startedAt, endedAt);

            String fileName = "Movies_" + startedAt.toString() + "-" + endedAt.toString() + ".xlsx";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/vnd.ms-excel");
            headers.add("Content-Disposition", "attachment; filename=" + fileName);

            return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(result));
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        // return for exception
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
