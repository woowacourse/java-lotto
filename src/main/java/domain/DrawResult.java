package domain;

public record DrawResult(LottoTicket winningLottoTicket, LottoNumber bonusNumber) {
    public DrawResult {
        validateDuplicateNumber(winningLottoTicket, bonusNumber);
    }

    private void validateDuplicateNumber(LottoTicket winningLottoNumbers, LottoNumber bonusNumber) {
        if (winningLottoNumbers.getLottoNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }
}
