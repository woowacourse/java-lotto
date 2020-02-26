package domain.numberscontainer;

import domain.LottoResult;

public class WinningNumbers extends LottoNumbers {

    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbersDto lottoNumbersDto, BonusNumberDTO bonusNumberDTO) {
        super(lottoNumbersDto);
        validateBonusNumber(bonusNumberDTO.getBonusNumber());
        this.bonusNumber = bonusNumberDTO.getBonusNumber();
    }

    private void validateBonusNumber(LottoNumber bonusNumber) {
        if (this.lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }

    public LottoResult getLottoResult(Ticket ticket) {
        int matchCount = findDuplicatedNumbers(ticket);
        boolean isBonus = ticket.contains(bonusNumber);
        return LottoResult.findLottoResult(matchCount, isBonus);
    }

    public int findDuplicatedNumbers(Ticket ticket) {
        return (int) this.lottoNumbers.stream()
                .filter(ticket::contains)
                .count();
    }
}