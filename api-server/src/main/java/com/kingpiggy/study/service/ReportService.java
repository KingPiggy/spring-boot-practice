package com.kingpiggy.study.service;

import com.kingpiggy.study.domain.entity.Movie;
import com.kingpiggy.study.domain.repository.MovieRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportService {

    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public ByteArrayInputStream getMoviesToExcel(LocalDate startedAt, LocalDate endedAt) throws IOException {
        // 1. Get Movie Data
        List<Movie> movies = movieRepository.findAllByPeriod(startedAt, endedAt);

        // 2. Create File
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // 3. Set Style
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 4. Create Sheets
        Sheet moviesSheet = workbook.createSheet("Movies");
        //moviesSheet.autoSizeColumn();

        // 5. Write Dashboard Sheet
        createDashboardSheet(movies, moviesSheet, headerCellStyle);

        // 6. Write File
        workbook.write(out);
        log.info("[ReportService:getMoviesToExcel] create movie report done. row count:[{}]", movies.size());
        return new ByteArrayInputStream(out.toByteArray());
    }

    public void createDashboardSheet(List<Movie> movies, Sheet moviesSheet, CellStyle headerCellStyle) {
        // 1. Create header row
        Row headerRow = moviesSheet.createRow(0);
        String[] headerStrings = {"Title", "Adult", "Overview", "Release Date"};

        int idx = 0;
        Cell headerCell = null;
        for (String s : headerStrings) {
            headerCell = headerRow.createCell(idx++);
            headerCell.setCellValue(s);
            headerCell.setCellStyle(headerCellStyle);
        }

        // 2. Create rows
        Row bodyRow = null;
        Cell bodyCell = null;
        int index = 1;
        for(Movie data : movies) {
            bodyRow = moviesSheet.createRow(index++);
            bodyCell = bodyRow.createCell(0);
            bodyCell.setCellValue(data.getTitle());
            bodyCell = bodyRow.createCell(1);
            bodyCell.setCellValue(data.getAdult());
            bodyCell = bodyRow.createCell(2);
            bodyCell.setCellValue(data.getOverview());
            bodyCell = bodyRow.createCell(3);
            bodyCell.setCellValue(data.getReleaseDate().toString());
        }

        // 3. Set Column Width
        for (int i=0; i<headerStrings.length; i++){
            moviesSheet.autoSizeColumn(i);
            moviesSheet.setColumnWidth(i, (moviesSheet.getColumnWidth(i))+(short)1024);
        }
    }

}
