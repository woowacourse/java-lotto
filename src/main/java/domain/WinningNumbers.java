package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

    private static final int NUMBER_RANGE_MINIMUM = 1;
    private static final int NUMBER_RANGE_MAXIMUM = 45;

    private static final String OUT_OF_RANGE_ERROR = "[ERROR] 1이상 45이하 값을 입력해주세요.";
    private static final String DUPLICATED_NUMBER_ERROR = "[ERROR] 당첨번호와 보너스볼은 중복될 수 없습니다.";

    private final List<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        checkDuplicate(winningNumbers, bonusNumber);
        this.winningNumbers = new ArrayList<>(winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return winningNumbers.contains(lottoNumber);
    }

    public boolean isEqualToBonusNumber(LottoNumber lottoNumber) {
        return bonusNumber.equals(lottoNumber);
    }

    private void checkDuplicate(List<LottoNumber> winningNumbers, LottoNumber bonusNumber) {
        List<LottoNumber> numbers = new ArrayList<>(winningNumbers);
        numbers.add(bonusNumber);
        Set<LottoNumber> noDuplicatedNumbers = new HashSet<>(numbers);
        if (numbers.size() != noDuplicatedNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_ERROR);
        }
    }


}
