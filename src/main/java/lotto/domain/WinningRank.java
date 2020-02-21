package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum WinningRank {
    FIRST_RANK(2_000_000_000, 6, false),
    SECOND_RANK(30_000_000, 5, true),
    THIRD_RANK(1_500_000, 5, false),
    FOURTH_RANK(50_000, 4, false),
    FIFTH_RANK(5_000, 3, false),
    NO_RANK(0, 0, false);

    private final int winningMoney;
    private final int winningBallCount;
    private final boolean existingBonusBall;

    WinningRank(int winningMoney, int winningBallCount, boolean existingBonusBall) {
        this.winningMoney = winningMoney;
        this.winningBallCount = winningBallCount;
        this.existingBonusBall = existingBonusBall;
    }

    public static WinningRank selectRank(int correctNumber, boolean isBonusNumber) {
        return Arrays.stream(values())
                .filter(result -> result.winningBallCount == correctNumber)
                .filter(result -> result.existingBonusBall == isBonusNumber)
                .findFirst()
                .orElse(NO_RANK);
    }

    public static List<WinningRank> generateWinningRank(WinningBalls winningBalls, LottoTickets lottoTickets) {
        List<WinningRank> winningRanks = new ArrayList<>();
        for (LottoTicket lottoTicket : lottoTickets.getLottoTickets()) {
            int correctNumber = winningBalls.hitLottoBalls(lottoTicket);
            boolean isBonusNumber = winningBalls.hitBonusBall(lottoTicket);
            winningRanks.add(WinningRank.selectRank(correctNumber, isBonusNumber));
        }
        return winningRanks;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public int getWinningBallCount() {
        return winningBallCount;
    }
}
