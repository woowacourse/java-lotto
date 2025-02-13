package domain;

import java.util.List;


public class Lotto {

    public static final int LOTTO_PRICE = 1000;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MAX_LOTTO_SIZE = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public String getNumbers() {
        return numbers.toString();
    }

    public MatchDto getMatchDto(List<Integer> winningNumbers, int bonusNumber) {
        int winningNumberCount = numbers.stream()
                .filter(winningNumbers::contains)
                .mapToInt(e -> 1)
                .sum();

        boolean hasBonusNumber = numbers.contains(bonusNumber);

        return new MatchDto(
                winningNumberCount,
                hasBonusNumber
        );
    }

}
