package domain.lottonumber;

import domain.result.LottoRank;

public class WinningNumbers {
    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbersDto lottoNumbersDto) {
        this.lottoNumbers = lottoNumbersDto.getLottoNumbers();
        this.bonusNumber = lottoNumbersDto.getBonusNumber();
    }

    public LottoRank findLottoRank(LottoTicket lottoTicket) {
        int matchingNumber = this.lottoNumbers.findNumberOfMatchingNumbers(lottoTicket.getLottoNumbers());
        boolean hasBonus = lottoTicket.contains(bonusNumber);

        return LottoRank.valueOf(matchingNumber, hasBonus);
    }
}