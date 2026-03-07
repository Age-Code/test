package org.example.greenlink.domain;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class DomainEnum {

    public enum Difficulty {
        EASY,
        NORMAL,
        HARD
    }

    public enum lightPref {
        LOW,
        MEDIUM,
        HIGH
    }

    public enum UserPlantStatus {
        GROWING,
        HARVESTABLE,
        HARVESTED,
        FAILED
    }

    public enum ItemType {
        POT,
        DECORATION,
        NUTRIENT,
        TITLE
    }

    public enum QuestType {
        DAILY,
        WEEKLY,
        ACHIEVEMENT
    }

    public enum UserQuestState {
        ONGOING,
        COMPLETED,
        CLAIMED
    }
}
