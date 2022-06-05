package com.kingpiggy.study.web;

import com.kingpiggy.study.enumclass.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BusinessExceptionTest {

    @Test
    @DisplayName("[success] BusinessException 발생 테스트")
    public void test() {
        assertThrows(BusinessException.class, this::throwEntityNotFoundException);
    }

    public void throwEntityNotFoundException() {
        throw new BusinessException(ErrorCode.ENTITY_NOT_FOUND);
    }

}
