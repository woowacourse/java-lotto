package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int START_NUMBER_OF_LOTTO_RANGE = 1;
    private static final int END_NUMBER_OF_LOTTO_RANGE = 45;
    private static final int BALL_NUMBER_OF_ONE_LOTTO = 6;
    private final List<Integer> randomNumbers;

    public Lotto(Set<Integer> randomNumbers) {
        validateSize(randomNumbers);
        validateNumberRange(randomNumbers);

        this.randomNumbers = new ArrayList<>(randomNumbers);
        Collections.sort(this.randomNumbers);
    }

    public List<Integer> getRandomNumbers() {
        return randomNumbers;
    }

    private void validateSize(final Set<Integer> winningNumbers) {
        if (winningNumbers.size() != BALL_NUMBER_OF_ONE_LOTTO) {
            throw new IllegalArgumentException("번호는 중복없이 6개의 숫자여야 합니다.");
        }
    }

    private void validateNumberRange(final Set<Integer> winningNumbers) {
        for (Integer winningNumber : winningNumbers) {
            if (winningNumber < START_NUMBER_OF_LOTTO_RANGE || winningNumber > END_NUMBER_OF_LOTTO_RANGE) {
                throw new IllegalArgumentException("숫자는 1~45 사이여야 합니다.");
            }
        }
    }

    public boolean contains(int number) {
        return randomNumbers.contains(number);
    }
}
