package com.kingpiggy.study.service.test;

import com.kingpiggy.study.domain.test.SimpleNumEntity;
import com.kingpiggy.study.domain.test.SimpleNumRepository;
import com.kingpiggy.study.domain.test.SimpleVersionEntity;
import com.kingpiggy.study.domain.test.SimpleVersionRepository;
import com.kingpiggy.study.enumclass.ErrorCode;
import com.kingpiggy.study.web.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * MyLockService class.
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
@Service
@RequiredArgsConstructor
public class MyLockService {

    private final SimpleVersionRepository simpleVersionRepository;
    private final SimpleNumRepository simpleNumRepository;

    @Transactional
    public void saveVersionData(String title) {
        SimpleVersionEntity version = simpleVersionRepository.findByTitle(title)
                .orElseGet(() -> SimpleVersionEntity.builder()
                .title(title)
                .num(10000)
                .build());

        simpleVersionRepository.save(version);
    }

    @Transactional
    public void saveNumData(String title) {
        SimpleNumEntity entity = simpleNumRepository.findByTitle(title)
                .orElseGet(() -> SimpleNumEntity.builder()
                .title(title)
                .num(10000)
                .build());

        simpleNumRepository.save(entity);
    }

    @Transactional
    public SimpleVersionEntity findVersionByTitle(String title) {
        return simpleVersionRepository.findByTitle(title)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }

    @Transactional
    public SimpleNumEntity findNumByTitle(String title) {
        return simpleNumRepository.findByTitle(title)
                        .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
    }


    /**
     * num만 바꿔도 version은 자동으로 업데이트 된다.
     *
     * @param title : id
     * @param num : 변경 값
     * @return : 결과
     */
    @Transactional
    public SimpleVersionEntity testOptimistic(String title, int num) {
        SimpleVersionEntity entity = simpleVersionRepository.findByTitleWithOptimistic(title)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));

        entity.setNum(entity.getNum() - num);

        simpleVersionRepository.saveAndFlush(entity);

        return entity;
    }

    /**
     * SELECT ... FROM ... FOR UPDATE 구문으로 호출됨
     *
     * @param title : id
     * @param num : 변경 값
     * @return : 결과
     */
    @Transactional
    public SimpleNumEntity testPessimistic(String title, int num) {
        SimpleNumEntity entity = simpleNumRepository.findByTitleWithPessimistic(title)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));

        entity.setNum(entity.getNum() - num);

        simpleNumRepository.saveAndFlush(entity);

        return entity;
    }

}
