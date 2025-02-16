package domain;

public record DrawResult(LottoNumbers winningLottoNumbers, int bonusNumber) {
    public DrawResult {
        validateDuplicateNumber(winningLottoNumbers, bonusNumber);
    }

    private void validateDuplicateNumber(LottoNumbers winningLottoNumbers, int bonusNumber) {
        if (winningLottoNumbers.containsLottoNumber(bonusNumber)) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }
}
