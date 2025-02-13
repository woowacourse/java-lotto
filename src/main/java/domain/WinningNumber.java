package domain;

import error.AppException;
import error.ErrorMessage;
import java.util.List;

public class WinningNumber {
    // 당첨 번호 Lotto
    private final Lotto winningLotto;
    // 보너스 번호
    private final BonusNumber bonusNumber;

    public WinningNumber(final Lotto winningLotto, final BonusNumber bonusNumber) {
        validateDuplicate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    // 중복 검증
    private void validateDuplicate(final Lotto winningLotto, final BonusNumber bonusNumber) {
        if (winningLotto.getNumbers().contains(bonusNumber.getValue())) {
            throw new AppException(ErrorMessage.INVALID_WINNING_NUMBER_DUPLICATE);
        }
    }

    // 랭크 반환
    public LottoRank calculateRankByLotto(final Lotto lotto) {
        final List<Integer> numbers = lotto.getNumbers();
        final boolean isBonus = numbers.contains(this.bonusNumber.getValue());
        return LottoRank.findByMatchedCountAndIsBonus(countMatchedNumbers(lotto), isBonus);
    }

    // 일치하는 로또 갯수 계산
    private int countMatchedNumbers(final Lotto lotto) {
        final List<Integer> numbers = lotto.getNumbers();
        return (int) numbers.stream()
                .filter(number -> winningLotto.getNumbers().contains(number))
                .count();
    }
}
