package domain;

public enum BonusMatch {
    MATCH,
    NOT_MATCH,
    NONE,
    ;

    public boolean isMatch(boolean isBonusMatch) {
        return switch (this) {
            case MATCH -> isBonusMatch;
            case NOT_MATCH -> !isBonusMatch;
            case NONE -> true;
        };
    }
}
