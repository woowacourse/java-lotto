package model;

import dto.LottoDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Rank {
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000),
    FAIL(0, 0);

    private final int matchNumber;
    private final int winningAmount;

    Rank(int matchNumber, int winningAmount) {
        this.matchNumber = matchNumber;
        this.winningAmount = winningAmount;
    }

    public static Rank getRank(WinningLotto winningLotto, LottoDto lottoDto) {
        int duplicateNumber = getDuplicateNumber(winningLotto, lottoDto);
        for (Rank rank : values()) {
            if (rank.matchNumber == 5 && isBonusMatch(winningLotto.getBonus(), lottoDto)) {
                return SECOND;
            }
            if (rank.matchNumber == duplicateNumber) {
                return rank;
            }
        }

        return FAIL;
    }

    private static int getDuplicateNumber(WinningLotto winningLotto, LottoDto lottoDto) {
        Set<Integer> union = new HashSet<>();
        union.addAll(winningLotto.getWinningNumbers());
        union.addAll(lottoDto.lotto());
        return 12 - union.size();
    }

    private static boolean isBonusMatch(int bonus, LottoDto lottoDto) {
        List<Integer> lottoNumbers = lottoDto.lotto();
        return lottoNumbers.contains(bonus);
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public int getWinningAmount() {
        return winningAmount;
    }
}
