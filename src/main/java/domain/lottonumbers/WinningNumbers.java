package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;
import domain.result.LottoRank;

public class WinningNumbers {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbersDto lottoNumbersDto) {
        validateDuplicatedBonusNumber(lottoNumbersDto.getLottoNumbers(), lottoNumbersDto.getBonusNumber());
        this.lottoNumbers = lottoNumbersDto.getLottoNumbers();
        this.bonusNumber = lottoNumbersDto.getBonusNumber();
    }

    public LottoRank findLottoRank(LottoTicket lottoTicket) {
        int matchingNumber = this.lottoNumbers.findNumberOfMatchingNumbers(lottoTicket.getLottoNumbers());
        boolean hasBonus = lottoTicket.contains(bonusNumber);

        return LottoRank.valueOf(matchingNumber, hasBonus);
    }

    private void validateDuplicatedBonusNumber(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 중복되지 않는 보너스 번호를 입력해주세요.");
        }
    }
}