package lotto.model;

import java.util.List;

import lotto.util.InputValidator;

public class WinningLotto {

    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, LottoNumber bonusNumber) {
        InputValidator.validateContain(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Rank calculate(List<LottoNumber> numbers) {
        int count = countMatchingNumber(numbers);
        return Rank.of(count, isBonusNumber(numbers, count));
    }

    private int countMatchingNumber(List<LottoNumber> numbers) {
        return (int)numbers.stream()
            .filter(winningLotto::contains)
            .count();
    }

    private boolean isBonusNumber(List<LottoNumber> numbers, int count) {
        if (count == 5) {
            return numbers.contains(bonusNumber);
        }
        return false;
    }
}
