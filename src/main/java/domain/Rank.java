package domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    MISS(0, 0);

    private int numberOfMatching;
    private int winningMoney;

    Rank (int numberOfMatching, int winningMoney) {
        this.numberOfMatching = numberOfMatching;
        this.winningMoney = winningMoney;
    }

    public static Rank of(int numberOfMatching, boolean BonusNumberMatch) {
        if(numberOfMatching == SECOND.numberOfMatching && BonusNumberMatch){
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank != SECOND)
                .filter(rank -> rank.hasSame(numberOfMatching))
                .findFirst()
                .orElse(MISS);
    }

    private boolean hasSame(int numberOfMatching) {
        return this.numberOfMatching == numberOfMatching;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public String getRankExplanation() {
        StringBuilder sb = new StringBuilder();

        sb.append(Integer.toString(numberOfMatching));
        sb.append("개 일치 ");
        sb.append("(" + Integer.toString(winningMoney) + "원)");
        return sb.toString();
    }
}
