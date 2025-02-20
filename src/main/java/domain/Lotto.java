package domain;

import dto.LottoDto;
import validation.LottoValidator;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoValidator.validateWinningNumbers(numbers);
        this.numbers = numbers;
    }

    public LottoDto getLottoDto() {
        return new LottoDto(numbers);
    }

    public Rank getRank(WinningLotto winningLotto) {
        int matchCount = (int) numbers.stream()
                .filter(winningLotto::containsNumber)
                .count();
        return Rank.fromResult(matchCount, winningLotto.hasBonusBall(numbers));
    }
}
