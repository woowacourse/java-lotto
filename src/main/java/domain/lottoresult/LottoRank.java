package domain.lottoresult;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NOTHING(-1, false, 0);

    private final int hitCount;
    private final boolean bonus;
    private final int winning;

    private LottoRank(int hitCount, boolean bonus, int winning) {
        this.hitCount = hitCount;
        this.bonus = bonus;
        this.winning = winning;
    }

    /**
     * calculateRank는 당첨번호의 개수와 보너스번호 포함여부를 전달받아, 몇 등인지를 LottoRank형으로 반환한다.
     * 이때, LottoRank에 정의된 SECOND와 같이 bonus가 true인 등수는 무조건 파라미터의 보너스도 true여야 한다
     * (그렇지 않으면 THIRD와 당첨번호가 같으므로 서로 구분할 수가 없다.)
     * 하지만, FOURTH나 FIRST에서 보듯 정의된 bonus가 false라면, 보너스 여부와 상관 없이 당첨번호 개수만 맞으면 된다.
     * 이를 해결하기 위해 (!rank.bonus || bonus)라는 식을 사용하였다.
     * 만약 rank의 보너스가 false이면 바로 조건을 통과하고,
     * 그렇지 않다면(즉, rank의 보너스가 true라면) 파라미터 보너스도 true여야만 조건을 통과한다.
     *
     * @param hitCount 당첨번호의 개수를 전달하는 정수형 변수이다.
     * @param bonus 보너스 번호가 포함되었는지 여부를 전달하는 boolean형 변수이다.
     * @return 전달받은 값을 바탕으로 몇 등인지를 LottoRank 값으로 반환한다.
     */
    public static LottoRank calculateRank(int hitCount, boolean bonus) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.hitCount == hitCount && (!rank.bonus || bonus))
                .findAny()
                .orElse(NOTHING);
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getWinning() {
        return winning;
    }

    public boolean hasBonus() {
        return bonus;
    }
}
