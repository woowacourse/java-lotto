package lotto.domain.lotto;

import lotto.domain.Rank;
import lotto.domain.util.CustomStringUtils;
import lotto.exception.InvalidLottoNumbersException;

import java.util.List;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(String inputLottoNumbers, String inputBonusNumber) {
        CustomStringUtils.checkIsBlank(inputLottoNumbers);
        CustomStringUtils.checkIsBlank(inputBonusNumber);

        List<Integer> lottoNumbers = CustomStringUtils.parseInts(inputLottoNumbers);
        int bonusNumber = CustomStringUtils.parseInt(inputBonusNumber);

        checkIsBonusNumberOverlap(lottoNumbers, bonusNumber);

        this.lotto = new Lotto(LottoNumber.getLottoNumbers(lottoNumbers));
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
    }

    private void checkIsBonusNumberOverlap(List<Integer> lottoNumbers, int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new InvalidLottoNumbersException("보너스 숫자가 이전에 입력한 숫자와 중복됩니다.");
        }
    }

    public Rank matchRank(Lotto lotto) {
        List<LottoNumber> winningNumbers = this.lotto.getNumbers();
        List<LottoNumber> lottoNumbers = lotto.getNumbers();
        boolean isBonusMatch = isBonusMatch(lottoNumbers);

        lottoNumbers.retainAll(winningNumbers);
        int countOfMatch = lottoNumbers.size();

        return Rank.valueOf(countOfMatch, isBonusMatch);
    }

    private boolean isBonusMatch(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }
}
