package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class WinningLotto {
    public static final int NORMAL_LOTTO_NUMBERS_LENGTH = 6;
    public static final String NON_NUMERIC_ERROR = "숫자만 입력 가능합니다.";
    public static final String NOT_SIX_NUMBERS_ERROR = "당첨 번호는 총 6개 이어야 합니다.";
    public static final String DUPLICATE_ERROR = "중복되는 번호는 안됩니다.";

    private final List<LottoNumber> winningLottoNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        validateSixNumbers(winningNumbers);
        validateDuplicates(winningNumbers);
        this.winningLottoNumbers = winningNumbers;

        validateDuplicatesWithWinningNumbers(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

//    public WinningLotto(String[] winningNumbers, LottoNumber bonusNumber) {
//        validateNumbers(winningNumbers);
//        this.winningLottoNumbers = createWinningLotto(winningNumbers);
//
////        validateBonusNumber(bonusNumber);
//        this.bonusNumber = bonusNumber;
//    }

    private List<LottoNumber> createWinningLotto(String[] numbers) {
        return Arrays.stream(numbers)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private void validateSixNumbers(List<LottoNumber> numbers) {
        if (numbers.size() != NORMAL_LOTTO_NUMBERS_LENGTH) {
            throw new IllegalArgumentException(NOT_SIX_NUMBERS_ERROR);
        }
    }

    private void validateDuplicates(List<LottoNumber> numbers) {
        Set<LottoNumber> numberGroup = new HashSet<>(numbers);
        if (numberGroup.size() != NORMAL_LOTTO_NUMBERS_LENGTH) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }

    private void validateDuplicatesWithWinningNumbers(LottoNumber number) {
        if (number.hasAnyMatchingNumber(winningLottoNumbers)) {
            throw new IllegalArgumentException();
        }
    }

    public List<LottoNumber> getWinningLottoNumbers() {
        return Collections.unmodifiableList(winningLottoNumbers);
    }

    public int getBonusNumberAsInt() {
        return bonusNumber.getNumber();
    }
}
