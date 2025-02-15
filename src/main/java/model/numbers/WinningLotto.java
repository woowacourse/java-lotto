package model.numbers;

import java.util.List;
import model.numbers.LottoNumbers;

public class WinningLotto {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_SIZE = 6;

    private final LottoNumbers winningNumbers;
    private final int bonusNumber;

    public WinningLotto(LottoNumbers winningNumbers, int bonusNumber) {
        validateBonusNumber(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(LottoNumbers lottoNumbers, int bonusNumber) {
        validateRange(bonusNumber);
        validateOverlap(lottoNumbers, bonusNumber);
    }

    private void validateRange(int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 번호는 1부터 45 사이여야 합니다.");
        }
    }


    private void validateOverlap(LottoNumbers lottoNumbers, int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 중복되어서는 안됩니다.");
        }
    }

    public int countOverlappedNumbers(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean isOverlappedBonusNumber(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }
}
