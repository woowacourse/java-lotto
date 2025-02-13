package domain;

import dto.WinningLottoDto;
import java.util.List;

public class WinningLotto {
    private final Lotto lotto;
    private final Integer bonusNumber;

    public WinningLotto(List<Integer> numbers, Integer bonusNumber) {
        this.lotto = new Lotto(numbers);
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public void validate(Integer bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateDuplicate(bonusNumber);
    }

    private void validateBonusNumberRange(Integer bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("로또 번호 범위 오류");
        }
    }

    private void validateDuplicate(Integer bonusNumber) {
        List<Integer> lottos = this.lotto.getSortedNumbers();
        if (lottos.contains(bonusNumber)) {
            throw new IllegalArgumentException("로또 번호 중복 오류");
        }
    }

    public WinningLottoDto getWinningLottoDto(){
        return new WinningLottoDto(lotto.getSortedNumbers(),bonusNumber);
    }
}
