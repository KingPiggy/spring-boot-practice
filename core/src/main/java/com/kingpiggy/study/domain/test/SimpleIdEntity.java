package com.kingpiggy.study.domain.test;

import com.kingpiggy.study.domain.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * SimpleIdEntity class.
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
@Data
@Builder
@Entity
@Table(name = "simple_id_data")
@AllArgsConstructor
@NoArgsConstructor
public class SimpleIdEntity extends BaseTimeEntity implements Persistable<Long> {

    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Override
    public boolean isNew() {
        return this.getCreatedTime() == null;
    }

}
