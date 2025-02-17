package model;

import java.util.List;

public class WinLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinLotto(List<Integer> winNumbers, Integer bonusNumber) {
        validateDuplicate(winNumbers, bonusNumber);
        this.lotto = new Lotto(winNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    public Integer countMatchNumber(Lotto lotto) {
        return this.lotto.countMatchNumber(lotto);
    }

    public Boolean bonusMatch(Lotto lotto) {
        return lotto.bonusMatch(this.bonusNumber);
    }

    private void validateDuplicate(List<Integer> winNumbers, Integer bonusNumber) {
        if (winNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 중복이 아닌 숫자를 입력해주세요");
        }
    }
}
