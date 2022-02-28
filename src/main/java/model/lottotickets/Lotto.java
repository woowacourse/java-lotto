package model.lottotickets;

import java.util.List;
import java.util.stream.Collectors;

import model.lottonumbergenerator.Generator;
import model.lottotickets.vo.Number;
import model.winning.Rank;

public class Lotto {
    private final List<Number> numbers;

    public Lotto(final Generator generator) {
        List<Integer> generatedNumbers = generator.generateNumbers();
        numbers = generatedNumbers.stream()
                .map(Number::new)
                .collect(Collectors.toList());
    }

    public Rank compareWithWinningNumber(final List<Integer> winningNumbers, final int bonusNumber) {
        return Rank.valueOf(countMatchNumber(winningNumbers), hasBonusNumber(bonusNumber));
    }

    private int countMatchNumber(final List<Integer> winningNumbers) {
        return (int) numbers.stream()
                .filter(number -> number.contain(winningNumbers))
                .count();
    }

    private boolean hasBonusNumber(final int bonusNumber) {
        return numbers.stream()
                .anyMatch(number -> number.contain(bonusNumber));
    }
}
