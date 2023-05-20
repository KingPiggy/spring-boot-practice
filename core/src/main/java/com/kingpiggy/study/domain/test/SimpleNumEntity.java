package com.kingpiggy.study.domain.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SimpleNumEntity class.
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
@Data
@Builder
@Entity
@Table(name = "simple_num_data")
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
public class SimpleNumEntity {

    @Id
    @Column
    private String title;

    @Column
    private Integer num;

}
