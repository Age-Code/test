package org.example.greenlink.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_username", columnNames = "username"),
        @UniqueConstraint(name = "uk_user_email", columnNames = "email")
})
public class User extends AuditingFields {
    String username;
    String password;
    String email;
    String nickname;
    String phoneNumber;
    String address;
    String university;

    @OneToMany(mappedBy = "user")
    private List<Attend> attends = new ArrayList<>();

    protected User(){}
    private User(String username, String password, String email, String nickname, String phoneNumber, String address, String university) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.university = university;
    }
    public static User of(String username, String password, String email, String nickname, String phoneNumber, String address) {
        return new User(username, password, email, nickname, phoneNumber, address, null); }

    // 호환용(기존 university 필드 사용 코드 대응)
    public static User of(String username, String password, String email, String university, String nickname) {
        return new User(username, password, email, nickname, null, null, university);
    }
}
