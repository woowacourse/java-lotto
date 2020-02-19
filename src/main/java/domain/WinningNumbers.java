package domain;

public class WinningNumbers extends LottoNumbersContainer {

    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbersDto lottoNumbersDto) {
        super(lottoNumbersDto);
        validateBonusNumber(lottoNumbersDto.getBonusNumber());
        this.bonusNumber = lottoNumbersDto.getBonusNumber();
    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        validateErrorBonusNumber(bonusNumber);
        validateDuplicatedBonusNumber(bonusNumber);
    }

    private void validateErrorBonusNumber(LottoNumber bonusNumber) {
        if (bonusNumber != LottoNumber.ERROR) {
            throw new IllegalArgumentException("잘못된 보너스 번호를 입력했습니다.");
        }
    }

    private void validateDuplicatedBonusNumber(LottoNumber bonusNumber) {
        if (this.lottoSixNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨번호와 보너스 번호가 중복됩니다.");
        }
    }
}