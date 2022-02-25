package lotto.model;

import java.util.List;

public class Lotto {

    private final List<Integer> numbers;
    private Rank rank;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
        this.rank = Rank.LOSER;
    }

    public Rank calculateRank(List<Integer> winningNumbers, int bonusNumber) {
        int count = countMatchingNumber(winningNumbers);
        boolean win = false;
        if (count == Rank.SECOND.getCount()) {
            win = containNumber(bonusNumber);
        }
        return this.rank = Rank.getRank(count, win);
    }

    private int countMatchingNumber(List<Integer> winningNumbers) {
        return (int) winningNumbers.stream()
                .filter(this::containNumber)
                .count();
    }

    private boolean containNumber(int number) {
        return this.numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public Rank getRank() {
        return rank;
    }
}
