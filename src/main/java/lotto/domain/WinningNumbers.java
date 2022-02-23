package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private static final String ERROR_NUMBER_SIX_MESSAGE = "로또 숫자는 6개여야 합니다.";
    private static final String ERROR_DUPLICATION_LOTTO_MESSAGE = "로또 숫자는 중복되면 안됩니다.";
    private static final String ERROR_DUPLICATION_BONUS_MESSAGE = "보너스 숫자는 로또 숫자와 중복되면 안됩니다.";

    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<LottoNumber> lottoNumbers, LottoNumber bonusNumber) {
        validateNumberSix(lottoNumbers);
        validateLottoNumbersDuplication(lottoNumbers);
        validateBonusNumbersDuplication(lottoNumbers, bonusNumber);

        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateNumberSix(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ERROR_NUMBER_SIX_MESSAGE);
        }
    }

    private void validateLottoNumbersDuplication(List<LottoNumber> numbers) {
        Set<LottoNumber> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_LOTTO_MESSAGE);
        }
    }

    private void validateBonusNumbersDuplication(
            List<LottoNumber> lottoNumbers,
            LottoNumber bonusNumber
    ) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATION_BONUS_MESSAGE);
        }
    }

    public boolean containsLottoNumber(LottoNumber number) {
        return lottoNumbers.contains(number);
    }

    public boolean equalsBonusNumber(LottoNumber number) {
        return bonusNumber.equals(number);
    }
}
