package lotto.domain;

import java.util.List;

public class WinLotto {

    private final List<LottoNumber> winNumbers;
    private final LottoNumber bonusNumber;

    public WinLotto(final List<LottoNumber> winNumbers, final LottoNumber bonusNumber) {
        checkDuplicateWinNumbers(winNumbers);
        checkDuplicateBonusNumber(winNumbers, bonusNumber);
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicateWinNumbers(final List<LottoNumber> winNumbers) {
        if (winNumbers.size() != calculateDistinctSize(winNumbers)) {
            throw new IllegalArgumentException("[ERROR] 중복된 당첨 번호가 존재합니다.");
        }
    }

    private long calculateDistinctSize(final List<LottoNumber> winNumbers) {
        return winNumbers.stream()
                .distinct()
                .count();
    }

    private void checkDuplicateBonusNumber(final List<LottoNumber> winNumbers, final LottoNumber bonusNumber) {
        if (winNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 볼이 당첨 번호와 중복됩니다.");
        }
    }
}
