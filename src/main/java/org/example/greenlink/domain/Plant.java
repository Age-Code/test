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
public class Plant extends AuditingFields {
    @Column(nullable = false)
    String name;
    @Lob
    String description;
    String category;
    @Enumerated(EnumType.STRING)
    DomainEnum.Difficulty difficulty;
    Long growthPeriodDays;
    @Enumerated(EnumType.STRING)
    DomainEnum.lightPref lightPref;
    Long waterPrefMlPerDay;
    String imageUrl;
    String unlockCondition;

    protected Plant(){}
    private Plant(String name, String description, String category, DomainEnum.Difficulty difficulty, Long growthPeriodDays, DomainEnum.lightPref lightPref, String imageUrl, String unlockCondition) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.difficulty = difficulty;
        this.growthPeriodDays = growthPeriodDays;
        this.lightPref = lightPref;
        this.imageUrl = imageUrl;
        this.unlockCondition = unlockCondition;
    }
    public static Plant of(String name, String description, String category, DomainEnum.Difficulty difficulty, Long growthPeriodDays, DomainEnum.lightPref lightPref, String imageUrl, String unlockCondition) {
        return new Plant(name, description, category, difficulty, growthPeriodDays, lightPref, imageUrl, unlockCondition); }
}
