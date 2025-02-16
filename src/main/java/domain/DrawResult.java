package domain;

import java.util.List;

public record DrawResult(List<LottoNumber> winningLottoNumbers, LottoNumber bonusNumber) {
    public DrawResult {
        validateDuplicateNumber(winningLottoNumbers, bonusNumber);
    }

    private void validateDuplicateNumber(List<LottoNumber> winningLottoNumbers, LottoNumber bonusNumber) {
        if (winningLottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }
}
