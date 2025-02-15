package model.numbers;

import java.util.List;

public class WinningLotto {

    private final LottoNumbers winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(LottoNumbers winningNumbers, LottoNumber bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        lottoNumbers.getNumbers()
                .forEach(number -> {
                    if (bonusNumber.isMatched(number)) {
                        throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 중복되어서는 안됩니다.");
                    }
                });
    }

    public int countOverlappedNumbers(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean isOverlappedBonusNumber(List<Integer> numbers) {
        return numbers.stream()
                .anyMatch(bonusNumber::isMatched);
    }
}
