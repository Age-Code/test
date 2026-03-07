package org.example.greenlink.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "attend", uniqueConstraints = {
        @UniqueConstraint(name = "uk_attend_user_date", columnNames = {"user_id", "attendDate"})
})
public class Attend extends AuditingFields {
    @Column(nullable = false)
    LocalDate attendDate;
    Long streakAfter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Attend(){}
    private Attend(LocalDate attendDate, Long streakAfter, User user) {
        this.attendDate = attendDate;
        this.streakAfter = streakAfter;
        this.user = user;
    }
    public static Attend of(LocalDate attendDate, Long streakAfter, User user) {
        return new Attend(attendDate, streakAfter, user);
    }
}
