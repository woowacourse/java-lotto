package domain;

import java.util.*;

public class Lotto {
    private static final String LOTTO_DUPLICATED_MESSAGE = "[ERROR] 로또 번호에 중복된 숫자가 들어가면 안됩니다.";
    private static final String LOTTO_SIZE_MESSAGE = "[ERROR] 로또의 숫자는 6개여야 합니다.";
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_START = 0;
    private static final int LOTTO_END = 6;
    private static final int LOTTO_SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    private void validateSize(List<Integer> lotto) {
        if (lotto.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(LOTTO_SIZE_MESSAGE);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (isDuplicated(numbers)) {
            throw new IllegalArgumentException(LOTTO_DUPLICATED_MESSAGE);
        }
    }

    private boolean isDuplicated(List<Integer> numbers) {
        return numbers.size() != numbers.stream()
                .distinct()
                .count();
    }

    public List<Integer> getLotto() {
        return new ArrayList<>(numbers);
    }

    public static Lotto generateNumber() {
        List<Integer> lottoRange = new ArrayList<>();
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoRange.add(i);
        }
        Collections.shuffle(lottoRange);
        List<Integer> numbers = lottoRange.subList(LOTTO_START, LOTTO_END);
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public Rank match(WinningNumber winningNumber) {
        int matchCount = getMatchCount(winningNumber);
        boolean hasBonusBall = winningNumber.isBonusBallMatch(numbers);
        return Rank.valueOf(matchCount, hasBonusBall);
    }

    private int getMatchCount(WinningNumber winningNumber) {
        return (int) numbers.stream().mapToInt(number -> number).filter(winningNumber::contains).count();

    }

}
