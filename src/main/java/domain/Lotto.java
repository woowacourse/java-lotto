package domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
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

    private void validateNumbers(List<Integer> numbers){
        if(numbers.stream().anyMatch(num -> num < 1 || num > 45)){
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이의 번호입니다.");
        }
    }
}
