package lotto.model.winning_lotto;

import java.util.List;
import lotto.model.lotto.Lotto;
import lotto.model.winning_lotto.vo.BonusNumber;
import lotto.model.winning_lotto.vo.WinningLottoNumbers;

public class WinningLotto {

    private final WinningLottoNumbers numbers;
    private final BonusNumber bonusNumber;

    private WinningLotto(WinningLottoNumbers numbers, BonusNumber bonusNumber) {
        validate(numbers, bonusNumber);
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(List<Integer> numbers, int bonusNumber) {
        return new WinningLotto(WinningLottoNumbers.from(numbers), BonusNumber.from(bonusNumber));
    }

    private void validate(WinningLottoNumbers numbers, BonusNumber bonusNumber) {
        if (numbers.contains(bonusNumber.getValue())) {
            throw new IllegalArgumentException("당첨 로또 번호는 보너스 번호를 포함할 수 없습니다.");
        }
    }

    public int getMatchCount(Lotto lotto) {

        return (int) lotto.getNumbers().stream()
                .filter(numbers::contains)
                .count();
    }

    public boolean isBonusMatch(Lotto lotto) {
        return lotto.contains(bonusNumber.getValue());
    }

    @Override
    public String toString() {
        return "WinningLotto{" +
                "numbers=" + numbers +
                ", bonusNumber=" + bonusNumber +
                '}';
    }
}
