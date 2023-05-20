package com.kingpiggy.study.service.test;

import com.kingpiggy.study.domain.test.AutoIncIdEntity;
import com.kingpiggy.study.domain.test.AutoIncIdRepository;
import com.kingpiggy.study.domain.test.SimpleIdEntity;
import com.kingpiggy.study.domain.test.SimpleIdRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * SimpleDataService class.
 * <PRE>
 * Describe here.
 * </PRE>
 *
 * <PRE>
 * <B>History:</B>
 * SeungHoon Lee, 0.1.0, Created at 2023.03.19
 * </PRE>
 *
 * @author : SEUNGHOON
 * @version : 0.1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleDataService {

    private final SimpleIdRepository simpleIdRepository;
    private final AutoIncIdRepository autoIncIdRepository;

    @PersistenceContext
    private final EntityManager em;

    @Transactional(rollbackFor = Exception.class)
    public void saveSimpleAutoIncWithError(String title, String description) {
        autoIncIdRepository.save(AutoIncIdEntity.builder()
                .title(title)
                .description(description)
                .build());

        int a = 10;
        int b = 0;
        log.info("오류 유발하기 {}", a/b); // RuntimeException(ArithmeticException) 오류 유발
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveSimpleAutoInc(String title, String description) {
        autoIncIdRepository.save(AutoIncIdEntity.builder()
                .title(title)
                .description(description)
                .build());
    }

    @Transactional(rollbackFor = Exception.class)
    public void bulkInsertForIdEntity(int totalCount) {
        log.info("[bulkInsertForIdEntity] bulk insert. total count is {}.", totalCount);
        simpleIdRepository.deleteAll();
        List<SimpleIdEntity> testData = createIdEntityList(totalCount);

        System.out.println(">>>>> damian");
        System.out.println(testData.get(0));
        System.out.println(testData.get(0).getCreatedTime());

        simpleIdRepository.saveAll(testData);
    }

    @Transactional(rollbackFor = Exception.class)
    public void bulkInsertForIdEntity2(int totalCount, int batchSize) {
        log.info("[bulkInsertForIdEntity2] bulk insert. total count is {}.", totalCount);
        simpleIdRepository.deleteAll();

        List<SimpleIdEntity> testData = createIdEntityList(totalCount);

        int i = 0;
        for (SimpleIdEntity entity : testData) {
            em.persist(entity);

            if(i % batchSize == 0) {
                em.flush();
                em.clear();
            }

            i++;
        }

        em.flush();
        em.clear();
    }

    @Transactional(rollbackFor = Exception.class)
    public void bulkInsertForAutoIncIdEntity(int totalCount) {
        log.info("[bulkInsertForAutoIncIdEntity] bulk insert. total count is {}.", totalCount);
        autoIncIdRepository.deleteAll();
        List<AutoIncIdEntity> testData = createAutoIncEntityList(totalCount);
        autoIncIdRepository.saveAll(testData);
    }

    @Transactional(rollbackFor = Exception.class)
    public void bulkUpdateForSimpleId(int totalCount) {
        log.info("[bulkUpdateForSimpleId] bulk update. total count is {}.", totalCount);

        List<SimpleIdEntity> testData = simpleIdRepository.findAllByOrderById();
        // List<SimpleIdEntity> testData2 = createIdEntityList(totalCount);

        int i = 0;
        for (SimpleIdEntity entity : testData) {
            String newTitle = i%2==0 ? "Even" : "Odd";
            entity.setTitle(newTitle);
            i++;
        }

        simpleIdRepository.saveAll(testData);
    }

    public List<SimpleIdEntity> createIdEntityList(int totalCount) {
        List<SimpleIdEntity> testData = new ArrayList<>();
        for (int i = 0; i < totalCount; i++) {
            testData.add(makeSimpleIdEntityForTest(i + 1));
        }
        return testData;
    }

    public List<AutoIncIdEntity> createAutoIncEntityList(int totalCount) {
        List<AutoIncIdEntity> testData = new ArrayList<>();
        for (int i = 0; i < totalCount; i++) {
            testData.add(makeAutoIncIdEntity(i + 1));
        }
        return testData;
    }

    private SimpleIdEntity makeSimpleIdEntityForTest(int i) {
        return SimpleIdEntity.builder()
                .id(Long.valueOf(i))
                .title("simple title - " + i)
                .description("simple desc - " + i)
                .build();
    }

    private AutoIncIdEntity makeAutoIncIdEntity(int i) {
        return AutoIncIdEntity.builder()
                .title("simple title - " + i)
                .description("simple desc - " + i)
                .build();
    }

}
