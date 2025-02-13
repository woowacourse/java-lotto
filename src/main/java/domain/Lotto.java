package domain;

import java.util.List;


public class Lotto {

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
