package com.kingpiggy.study.service.test;

import com.kingpiggy.study.BaseLocalTest;
import com.kingpiggy.study.domain.test.SimpleNumEntity;
import com.kingpiggy.study.domain.test.SimpleNumRepository;
import com.kingpiggy.study.domain.test.SimpleVersionEntity;
import com.kingpiggy.study.domain.test.SimpleVersionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MyLockServiceTest extends BaseLocalTest {

    @Autowired
    private MyLockService myLockService;
    
    @Test
    @DisplayName("test- testOptimistic")
    void test_testOptimistic() throws Exception {
        // given
        String title = "낙관적";
        myLockService.saveVersionData(title);
        SimpleVersionEntity origin = myLockService.findVersionByTitle(title);
        System.out.println("[test] origin : " + origin); // version 0, 10000

        // when
        SimpleVersionEntity data1 = myLockService.testOptimistic(title, 1000);
        System.out.println("[test] Data1 : " + data1);  // version 1, 9000

        SimpleVersionEntity data2 = myLockService.testOptimistic(title, 2000);
        System.out.println("[test] Data2 : " + data2); // version 2, 7000

        // then
        SimpleVersionEntity result = myLockService.findVersionByTitle(title);
        System.out.println("[test] Result : " + result); // version 2, 7000
    }

    @Test
    @DisplayName("test- testPessimistic")
    void test_testPessimistic() throws Exception {
        // given
        String title = "비관적";
        myLockService.saveNumData(title);

        SimpleNumEntity origin = myLockService.findNumByTitle(title);
        System.out.println("[test] origin : " + origin); // 10000

        // when
        SimpleNumEntity data1 = myLockService.testPessimistic(title, 1000);
        System.out.println("[test] Data1 : " + data1);  // 9000

        SimpleNumEntity data2 = myLockService.testPessimistic(title, 2000);
        System.out.println("[test] Data2 : " + data2); // 7000

        // then
        SimpleNumEntity result = myLockService.findNumByTitle(title);
        System.out.println("[test] Result : " + result); // 7000

    }
    
}