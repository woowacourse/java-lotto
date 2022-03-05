package lotto.domain;

import static lotto.domain.LottoTicket.LOTTO_NUMBER_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningLotto {

    private static final String ERROR_DUPLICATION_WINNING_NUMBERS = "당첨 번호가 서로 중복되었습니다.";
    private static final String ERROR_NOT_MATCH_NUMBER_COUNT = "당첨 번호는 6개를 입력해주세요.";
    private static final String ERROR_DUPLICATION_BONUS_NUMBER = "지난주 당첨 번호와 중복되는 숫자입니다.";

    private final Set<LottoNumber> winningNumbers;
    private final LottoNumber bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = toLottoNumbersSet(winningNumbers);
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
        validateWinningNumbersDuplication(winningNumbers.size());
        validateWinningNumbersCount();
        validateBonusNumberDuplication();
    }

    private Set<LottoNumber> toLottoNumbersSet(List<Integer> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet());
    }

    private void validateWinningNumbersDuplication(int inputNumbersSize) {
        if (this.winningNumbers.size() != inputNumbersSize) {
            throw new RuntimeException(ERROR_DUPLICATION_WINNING_NUMBERS);
        }
    }

    private void validateWinningNumbersCount() {
        if (winningNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new RuntimeException(ERROR_NOT_MATCH_NUMBER_COUNT);
        }
    }

    private void validateBonusNumberDuplication() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new RuntimeException(ERROR_DUPLICATION_BONUS_NUMBER);
        }
    }

    public Set<LottoNumber> getWinningNumbers() {
        return new HashSet<>(winningNumbers);
    }

    public LottoNumber getBonusNumber() {
        return LottoNumber.valueOf(bonusNumber.getNumber());
    }
}
