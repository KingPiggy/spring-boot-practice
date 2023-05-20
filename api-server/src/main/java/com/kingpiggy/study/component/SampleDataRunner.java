package com.kingpiggy.study.component;

import com.kingpiggy.study.service.test.SimpleDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * SampleDataRunner class.
 * <PRE>
 * Describe here.
 * </PRE>
 *
 * <PRE>
 * <B>History:</B>
 * SeungHoon Lee, 0.1.0, Created at 2023.04.22
 * </PRE>
 *
 * @author : SEUNGHOON
 * @version : 0.1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SampleDataRunner implements CommandLineRunner {

    private final SimpleDataService simpleDataService;

    @Override
    public void run(String... args) throws Exception {

    }

}
