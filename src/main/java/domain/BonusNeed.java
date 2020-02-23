package domain;

public enum BonusNeed {
    NEED {
        @Override
        boolean isNeedBonus(boolean bonus) {
            return bonus;
        }
    },
    NOT_NEED {
        @Override
        boolean isNeedBonus(boolean bonus) {
            return !bonus;
        }
    },
    DONT_CARE {
        @Override
        boolean isNeedBonus(boolean bonus) {
            return true;
        }
    };

    abstract boolean isNeedBonus(boolean bonus);
}
