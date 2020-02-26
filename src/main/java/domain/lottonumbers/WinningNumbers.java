package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;
import domain.result.LottoRank;

public class WinningNumbers extends LottoNumbers {

    private final LottoNumber bonusNumber;

    public WinningNumbers(WinningNumbersDto winningNumbersDto) {
        super(winningNumbersDto.getLottoNumbers());
        this.bonusNumber = winningNumbersDto.getBonusNumber();
    }

    public LottoRank findLottoRank(LottoTicket lottoTicket) {
        int matchingNumber = this.findNumberOfMatchingNumbers(lottoTicket);
        boolean hasBonus = lottoTicket.contains(bonusNumber);

        return LottoRank.valueOf(matchingNumber, hasBonus);
    }

    private int findNumberOfMatchingNumbers(LottoNumbers comparingNumber) {
        return (int) this.lottoNumbers.stream()
                .filter(comparingNumber::contains)
                .count();
    }
}