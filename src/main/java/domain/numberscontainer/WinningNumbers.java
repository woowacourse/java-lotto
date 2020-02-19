package domain.numberscontainer;

import domain.LottoNumber;

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
        if (bonusNumber == LottoNumber.ERROR) {
            throw new IllegalArgumentException("1~45 사이의 보너스 번호를 입력해주세요.");
        }
    }

    private void validateDuplicatedBonusNumber(LottoNumber bonusNumber) {
        if (this.lottoSixNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }

    public int findDuplicatedNumbers(Ticket ticket) {
        return this.lottoSixNumbers.stream()
                .filter(number -> ticket.contains(number))
                .mapToInt(number -> 1)
                .sum();
    }
}