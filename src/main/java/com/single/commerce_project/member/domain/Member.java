package com.single.commerce_project.member.domain;

import com.single.commerce_project.member.type.MemberStatus;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Member extends BaseTimeEntity{

    @Id
    private String userId;

    @Column(unique = true)
    private String email;

    private String userName;
    private String password;
    private String phone;

    @DateTimeFormat(pattern = "yyyy-mm-dd'T'HH:mm:ss'")
    private LocalDateTime unRegisteredAt;
    private long amount;
    private boolean adminYn;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    private LocalDateTime emailAuthAt;
    private String emailAuthKey;


}
