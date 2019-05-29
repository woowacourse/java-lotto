package lotto.domain;

public enum WinnerType {
    FIRST(6, false), SECOND(5, true), THIRD(5, false),
    FOURTH(4, false), FIFTH(3, false);

    private final int matchNumber;
    private final boolean bonus;

    private WinnerType(int matchNumber, boolean bonus) {
        this.matchNumber = matchNumber;
        this.bonus = bonus;
    }

    public static WinnerType valueOf(int matchNumber) {
        for (WinnerType value : values()) {
            if (value.matchNumber == matchNumber) {
                return value;
            }
        }
        throw new IllegalArgumentException("당첨 조건을 찾을 수 없습니다.");
    }


}
