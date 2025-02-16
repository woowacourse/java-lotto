package domain;

import java.util.List;

public class DrawResult {
    private final List<LottoNumber> winningLottoNumbers;
    private final LottoNumber bonusNumber;

    public DrawResult(List<LottoNumber> winningLottoNumbers, LottoNumber bonusNumber) {
        validateDuplicateNumber(winningLottoNumbers, bonusNumber);
        this.winningLottoNumbers = winningLottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateDuplicateNumber(List<LottoNumber> winningLottoNumbers, LottoNumber bonusNumber) {
        if (winningLottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }

    public List<LottoNumber> getWinningLottoNumbers() {
        return winningLottoNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
