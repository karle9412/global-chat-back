package com.green.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.mapping.Array;
import org.hibernate.id.UUIDGenerator;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@DynamicInsert
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TESTUSER", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id; // 유저에게 고유하게 부여되는 id.

    @Column(nullable = false)
    private String username; // 유저의 이름

    @Column(nullable = false)
    private String email; // 유저의 email, 아이디와 같은 기능을 한다.

    @Column(nullable = false)
    private String password; // 패스워드. null이 가능한 이유는 oAuth로 페이스북이나 트위터같은 제3의 어플리케이션을 통해 로그인 할 수 있게 하기 위함이다.

    @Column(nullable = false)
    private String phonenumber;

    @Column(nullable = true)
    private String local;

    @Column(columnDefinition = "varchar(255) default 'user'")
    private String role;

}
