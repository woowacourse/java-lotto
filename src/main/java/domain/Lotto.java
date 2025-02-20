package domain;

import java.util.List;

public class Lotto {
    public static final int LOTTO_NUMBER_MIN = 1;
    public static final int LOTTO_NUMBER_MAX = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoNumbers(numbers);
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

    private void validateLottoNumbers(List<Integer> lottoNumbers) {
        if (lottoNumbers.stream().anyMatch(this::isOutOfRange)) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBER_MIN + " ~ " + LOTTO_NUMBER_MAX + " 사이의 번호입니다.");
        }
    }

    private boolean isOutOfRange(int lottoNumber) {
        return lottoNumber < LOTTO_NUMBER_MIN || lottoNumber > LOTTO_NUMBER_MAX;
    }
}
