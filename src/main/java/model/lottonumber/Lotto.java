package model.lottonumber;

import java.util.List;
import java.util.stream.Collectors;

import model.generator.Generator;
import model.lottonumber.vo.Number;
import model.rank.Rank;

public class Lotto {
    private final List<Number> numbers;

    public Lotto(final Generator generator) {
        List<Integer> generatedNumbers = generator.generateNumbers();
        numbers = generatedNumbers.stream()
                .map(Number::new)
                .collect(Collectors.toList());
    }

    public Lotto(final List<Integer> numbers) {
        this.numbers = numbers.stream()
                .map(Number::new)
                .collect(Collectors.toList());
    }

    public Rank findWinningRank(final WinningNumbers winningNumbers) {
        int matchCount = countMatch(winningNumbers.getWinningNumbers());
        boolean hasBonus = hasBonus(winningNumbers.getBonusNumber());

        return Rank.valueOf(matchCount, hasBonus);
    }

    private int countMatch(final List<Number> winningNumbers) {
        return (int) numbers.stream()
                .filter(number -> number.hasSameNumber(winningNumbers))
                .count();
    }

    private boolean hasBonus(final Number bonusNumber) {
        return numbers.stream()
                .anyMatch(number -> number.equals(bonusNumber));
    }

    public List<Integer> getLottoNumbers() {
        return numbers.stream()
                .map(Number::getNumber)
                .collect(Collectors.toList());
    }
}
