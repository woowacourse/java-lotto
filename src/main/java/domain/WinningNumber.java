package domain;

import exception.AppException;

import java.util.List;

public class WinningNumber {

    public static final String INVALID_WINNING_NUMBER_DUPLICATE = "보너스 번호는 로또 번호와 중복될 수 없습니다.";
    public static final String INVALID_WINNING_NUMBERS_FORMAT = "당첨 번호들은 숫자여야 합니다.";

    private final Lotto winningLotto;
    private final BonusNumber bonusNumber;

    public WinningNumber(final Lotto winningLotto, final BonusNumber bonusNumber) {
        validateDuplicate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicate(final Lotto winningLotto, final BonusNumber bonusNumber) {
        if (winningLotto.getNumbers().contains(bonusNumber.getValue())) {
            throw new AppException(INVALID_WINNING_NUMBER_DUPLICATE);
        }
    }

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
