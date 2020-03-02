package domain;

import java.util.stream.Stream;

public enum RankType {
    MATCH_NOTHING(0, 0),
    MATCH_THREE(3, 5_000),
    MATCH_FOUR(4, 50_000),
    MATCH_FIVE(5, 1_500_000),
    MATCH_FIVE_WITH_BONUS(5, 30_000_000),
    MATCH_SIX(6, 2_000_000_000);

    private static final int BONUS_NUMBER_MATCH_COUNT = 5;

    private int number;
    private long prize;

    RankType(int number, long prize) {
        this.number = number;
        this.prize = prize;
    }

    public static RankType of(LottoTicket lottoTicket, WinningLottoTicket winningLottoTicket) {
        int correctCount = winningLottoTicket.countMatchNumber(lottoTicket);
        if (correctCount == BONUS_NUMBER_MATCH_COUNT && isBonusCondition(lottoTicket, winningLottoTicket)) {
            return MATCH_FIVE_WITH_BONUS;
        }
        return Stream.of(RankType.values())
                .filter(rankType -> rankType.number == correctCount)
                .findFirst()
                .orElse(MATCH_NOTHING);
    }

    private static boolean isBonusCondition(LottoTicket lottoTicket, WinningLottoTicket winningLottoTicket) {
        return winningLottoTicket.isMatchBonusNumber(lottoTicket);
    }

    public int getNumber() {
        return number;
    }

    public long getPrize() {
        return prize;
    }
}
