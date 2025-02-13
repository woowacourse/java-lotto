package model;

import dto.LottoDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Rank {
    FIFTH(3, 5_000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false),
    FAIL(0, 0, false);

    private int matchNumber;
    private int winningAmount;
    private boolean isBonus;

    Rank(int matchNumber, int winningAmount, boolean isBonus) {
        this.matchNumber = matchNumber;
        this.winningAmount = winningAmount;
        this.isBonus = isBonus;
    }

    public static Rank getRank(WinningLotto winningLotto, LottoDto lottoDto) {
        int duplicateNumber = getDuplicateNumber(winningLotto, lottoDto);
        for(Rank rank : values()) {
            if(rank.matchNumber == 5 && isBonusMatch(winningLotto.getBonus(), lottoDto)) {
                return SECOND;
            }
            if(rank.matchNumber == duplicateNumber) {
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
}
