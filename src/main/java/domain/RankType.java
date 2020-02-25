package domain;

import java.util.function.Function;
import java.util.stream.Stream;

public enum RankType {
    MATCH_NOTHING(0, "0개 일치 (0원) - ", value -> value),
    MATCH_THREE(3, "3개 일치 (5000원) - ", value -> value * 5_000),
    MATCH_FOUR(4, "4개 일치 (50000원) - ", value -> value * 50_000),
    MATCH_FIVE(5, "5개 일치 (1500000원) - ", value -> value * 1_500_000),
    MATCH_FIVE_WITH_BONUS(5, "5개 일치, 보너스 볼 일치(30000000원) - ", value -> value * 30_000_000),
    MATCH_SIX(6, "6개 일치 (2000000000원) - ", value -> value * 2_000_000_000);

    private static final int BONUS_BALL_MATCH_COUNT = 5;

    private int number;
    private String printStr;
    private Function<Integer, Integer> expression;

    RankType(int number, String printStr, Function<Integer, Integer> expression) {
        this.number = number;
        this.printStr = printStr;
        this.expression = expression;
    }

    public static RankType of(LottoTicket lottoTicket, WinningLottoTicket winningLottoTicket) {
        int correctCount = winningLottoTicket.getCorrectCount(lottoTicket);
        if (correctCount == BONUS_BALL_MATCH_COUNT && isBonusCondition(lottoTicket, winningLottoTicket)) {
            return MATCH_FIVE_WITH_BONUS;
        }
        return Stream.of(RankType.values())
                .filter(rankType -> rankType.number == correctCount)
                .findFirst()
                .orElse(MATCH_NOTHING);
    }

    private static boolean isBonusCondition(LottoTicket lottoTicket, WinningLottoTicket winningLottoTicket) {
        return winningLottoTicket.isMatchBonusBall(lottoTicket);
    }

    public int calculate(int value) {
        return expression.apply(value);
    }

    public String getPrintStr() {
        return printStr;
    }
}
