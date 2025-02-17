package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import view.util.LottoNumberGenerator;

public class Lotto {
    private static final int START_NUMBER_OF_LOTTO_RANGE = 1;
    private static final int END_NUMBER_OF_LOTTO_RANGE = 45;
    private static final int BALL_NUMBER_OF_ONE_LOTTO = 6;
    private final List<Integer> randomNumbers;

    public Lotto(final List<Integer> winningNumbers) {
        validateSize(winningNumbers);
        validateDuplicate(winningNumbers);

        Set<Integer> randomNumbers = new HashSet<>(winningNumbers);
        validateNumberRange(randomNumbers);

        this.randomNumbers = toSortedList(randomNumbers);
    }

    public Lotto(LottoNumberGenerator generator) {
        Set<Integer> randomNumbers = makeRandomNumbers(generator);
        validateNumberRange(randomNumbers);

        this.randomNumbers = toSortedList(randomNumbers);
    }


    private List<Integer> toSortedList(Set<Integer> randomNumbers) {
        List<Integer> randomNumberList = new ArrayList<>(randomNumbers);
        Collections.sort(randomNumberList);
        return randomNumberList;
    }

    private Set<Integer> makeRandomNumbers(LottoNumberGenerator numberGenerator) {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < BALL_NUMBER_OF_ONE_LOTTO) {
            numbers.add(numberGenerator.generate());
        }
        return numbers;
    }

    public List<Integer> getRandomNumbers() {
        return randomNumbers;
    }

    private void validateSize(List<Integer> winningNumbers) {
        if (winningNumbers.size() != BALL_NUMBER_OF_ONE_LOTTO) {
            throw new IllegalArgumentException("번호는 6개의 숫자여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> winningNumbers) {
        if(isDuplicatedNumbers(winningNumbers)){
            throw new IllegalArgumentException("당첨 번호는 중복될 수 없습니다.");
        }
    }

    private boolean isDuplicatedNumbers(List<Integer> winningNumbers) {
        return winningNumbers.size() != new HashSet<>(winningNumbers).size();
    }


    private void validateNumberRange(Set<Integer> winningNumbers) {
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
