package com.kingpiggy.study;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * BaseLocalTest class.
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
@Transactional
@ActiveProfiles({"test", "local"})
@SpringBootTest
public class BaseLocalTest {
}
