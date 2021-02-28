package domain;

import java.util.List;

public class WinningNumbers {

    private static final String BONUS_DUPLICATE_ERROR = "당첨 번호에 보너스 번호가 포함되어 있습니다.";

    private final LottoTicket winningTicket;
    private final LottoNumber bonusNumber;

    private WinningNumbers(final List<Integer> winningNumbers, final int bonusNumber) {
        validateDistinctBonus(winningNumbers, bonusNumber);
        this.winningTicket = LottoTicket.valueOf(winningNumbers);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
    }

    public static WinningNumbers valueOf(final String numbers, final int bonusNumber) {
        List<Integer> winningNumbers = LottoMachine.convertToInt(numbers);
        return new WinningNumbers(winningNumbers, bonusNumber);
    }

    private void validateDistinctBonus(final List<Integer> winningNumbers, final int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_DUPLICATE_ERROR);
        }
    }

    public Ranking checkRanking(LottoTicket lottoTicket) {
        int matching = winningTicket.countMatchingNumbers(lottoTicket);
        boolean bonusMatching = lottoTicket.contains(bonusNumber);
        return Ranking.select(matching, bonusMatching);
    }
}
