package lotto.domain.lotto;

import lotto.domain.Rank;
import lotto.exception.InvalidLottoNumbersException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(String inputLottoNumbers, String inputBonusNumber) {
        checkIsBlank(inputLottoNumbers);
        checkIsBlank(inputBonusNumber);

        List<Integer> lottoNumbers = toInt(Arrays.asList(inputLottoNumbers.split(",")));
        int bonusNumber = parseInt(inputBonusNumber);

        checkIsBonusNumberOverlap(lottoNumbers, bonusNumber);

        this.lotto = new Lotto(LottoNumber.getLottoNumbers(lottoNumbers));
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
    }

    private void checkIsBonusNumberOverlap(List<Integer> lottoNumbers, int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new InvalidLottoNumbersException("보너스 숫자가 이전에 입력한 숫자와 중복됩니다.");
        }
    }

    private void checkIsBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new InvalidLottoNumbersException("아무것도 입력하지 않으셨습니다.");
        }
    }

    private List<Integer> toInt(List<String> strings) {
        List<Integer> numbers = new ArrayList<>();

        for (String string : strings) {
            numbers.add(parseInt(string));
        }
        return numbers;
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidLottoNumbersException("숫자가 아닌 값이 포함되어 있습니다.");
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
