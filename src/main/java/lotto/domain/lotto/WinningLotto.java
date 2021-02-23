package lotto.domain.lotto;

import java.util.List;

public class WinningLotto {

    public static final String LOTTO_LINE_NUMBER_BONUS_LOTTO_NUMBER_DUPLICATE_ERROR = "[Error] 로또 번호와 보너스 번호가 중복됩니다.";
    private final LottoLine winningLottoLine;
    private final LottoNumber bonusLottoNumber;

    public WinningLotto(LottoLine winningLottoLine, LottoNumber bonusLottoNumber) {
        if (winningLottoLine.hasLottoNumber(bonusLottoNumber)) {
            throw new IllegalArgumentException(
                LOTTO_LINE_NUMBER_BONUS_LOTTO_NUMBER_DUPLICATE_ERROR);
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
