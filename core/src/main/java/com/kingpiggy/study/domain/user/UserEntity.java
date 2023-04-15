package com.kingpiggy.study.domain.user;

import javax.persistence.*;

import com.kingpiggy.study.common.persistence.BaseTimeEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String refreshToken;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

}
