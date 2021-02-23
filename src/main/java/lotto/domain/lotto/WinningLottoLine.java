package lotto.domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_LINE_NUMBER_BONUS_LOTTO_NUMBER_DUPLICATE_ERROR;

import java.util.List;

public class WinningLottoLine {

    private final LottoLine winningLottoLine;
    private final LottoNumber bonusLottoNumber;

    public WinningLottoLine(LottoLine winningLottoLine, LottoNumber bonusLottoNumber) {
        if (winningLottoLine.isContainLottoNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException(
                LOTTO_LINE_NUMBER_BONUS_LOTTO_NUMBER_DUPLICATE_ERROR.getMessage());
        }
        this.winningLottoLine = winningLottoLine;
        this.bonusLottoNumber = bonusLottoNumber;
    }

    public boolean isContainBonusLottoNumber(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.contains(bonusLottoNumber);
    }

    public int getLottoNumberMatchCount(List<LottoNumber> lottoNumbers) {
        List<LottoNumber> winningLottoNumbers = winningLottoLine.getLottoNumbers();
        return (int) winningLottoNumbers.stream()
            .filter(lottoNumbers::contains)
            .count();
    }

}
