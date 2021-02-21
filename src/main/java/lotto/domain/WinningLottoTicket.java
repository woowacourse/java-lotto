package lotto.domain;

import java.util.List;

public class WinningLottoTicket {
    private static final String DUPLICATION_NUMBER = "보너스 볼 번호는 당첨 번호와 중복될 수 없습니다.";

    private final LottoTicket lottoTicket;
    private final LottoNumber bonusLottoNumber;

    private WinningLottoTicket(LottoTicket lottoTicket, LottoNumber bonusLottoNumber) {
        this.lottoTicket = lottoTicket;
        this.bonusLottoNumber = bonusLottoNumber;
    }

    public static WinningLottoTicket of(List<Integer> winningNumbers, int bonusBallNumber) {
        validateDuplication(winningNumbers, bonusBallNumber);
        LottoTicket lottoTicket = LottoTicket.from(winningNumbers);
        LottoNumber bonusLottoNumber = LottoNumber.from(bonusBallNumber);
        return new WinningLottoTicket(lottoTicket, bonusLottoNumber);
    }

    private static void validateDuplication(List<Integer> winningNumbers, int bonusBallNumber) {
        if (winningNumbers.contains(bonusBallNumber)) {
            throw new IllegalArgumentException(DUPLICATION_NUMBER);
        }
    }

    public LottoRank compareNumbers(LottoTicket lottoTicket) {
        int matchCounts = lottoTicket.checkMatchCounts(this.lottoTicket);
        boolean isBonusBall = lottoTicket.contains(bonusLottoNumber);
        return LottoRank.of(matchCounts, isBonusBall);
    }
}
