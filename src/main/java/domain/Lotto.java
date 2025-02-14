package domain;

import java.util.List;

public class Lotto {
    private final int LOTTO_NUMBER_MIN = 1;
    private final int LOTTO_NUMBER_MAX = 45;

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

    private void validateLottoNumbers(List<Integer> lottoNumbers){
        if(lottoNumbers.stream().anyMatch(lottoNumber -> lottoNumber < LOTTO_NUMBER_MIN || lottoNumber > LOTTO_NUMBER_MAX)){
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이의 번호입니다.");
        }
    }
}
