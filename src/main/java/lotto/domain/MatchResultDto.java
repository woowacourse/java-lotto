package lotto.domain;

public class MatchResultDto {

    private final int matchCount;
    private final boolean containsBonusNumber;

    public MatchResultDto(int matchCount, boolean containsBonusNumber) {
        this.matchCount = matchCount;
        this.containsBonusNumber = containsBonusNumber;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isContainsBonusNumber() {
        return containsBonusNumber;
    }
}
