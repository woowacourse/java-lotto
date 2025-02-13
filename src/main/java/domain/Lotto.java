package domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public String toString() {
        return numbers.toString();
    }

    public Rank getRank(List<Integer> winningNumbers, int bonusBall) {
        int matchCount = (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();

        return Rank.fromResult(matchCount, numbers.contains(bonusBall));
    }
}
