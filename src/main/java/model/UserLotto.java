package model;

import java.util.HashSet;
import java.util.List;
import view.util.LottoConstants;

public class UserLotto {
    private Lotto winningNumbers;

    public UserLotto(List<Integer> userInputNumbers) {
        validateSize(userInputNumbers);
        validateDuplicate(userInputNumbers);
        validateNumberRange(userInputNumbers);

        this.winningNumbers = new Lotto(new HashSet<>(userInputNumbers));
    }

    private void validateSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != LottoConstants.BALL_NUMBER_OF_ONE_LOTTO) {
            throw new IllegalArgumentException("6개의 숫자만 입력해주세요.");
        }
    }

    private void validateDuplicate(List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != winningNumbers.size()) {
            throw new IllegalArgumentException("숫자는 중복될 수 없습니다.");
        }
    }

    private void validateNumberRange(List<Integer> winningNumbers) {
        for (Integer winningNumber : winningNumbers) {
            if (winningNumber < LottoConstants.START_NUMBER_OF_LOTTO_RANGE || winningNumber > LottoConstants.END_NUMBER_OF_LOTTO_RANGE) {
                throw new IllegalArgumentException("숫자는 1~45 사이여야 합니다.");
            }
        }
    }

    public void isDuplicateBonusNumber(int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 번호와 당첨 번호는 중복되어서는 안됩니다.");
        }
    }

    public int calculateMatchCount(Lotto lotto) {
        return (int) winningNumbers.getRandomNumbers()
                .stream()
                .filter(lotto::contains)
                .count();
    }

}
