package model.lottotickets;

import java.util.List;
import java.util.stream.Collectors;

import model.lottonumbergenerator.Generator;
import model.lottotickets.vo.Number;
import model.winning.Rank;
import model.winning.WinningNumbers;

public class Lotto {
    private final List<Number> numbers;

    public Lotto(final Generator generator) {
        List<Integer> generatedNumbers = generator.generateNumbers();
        numbers = generatedNumbers.stream()
                .map(Number::new)
                .collect(Collectors.toList());
    }

    public Rank compareWithWinningNumber(final WinningNumbers winningNumbers) {
        int matchCount = countMatchNumber(winningNumbers.getWinningNumbers());
        boolean hasBonus = hasBonusNumber(winningNumbers.getBonusNumber());

        return Rank.valueOf(matchCount, hasBonus);
    }

    private int countMatchNumber(final List<Number> winningNumbers) {
        return (int) numbers.stream()
                .filter(number -> number.hasSameNumber(winningNumbers))
                .count();
    }

    private boolean hasBonusNumber(final Number bonusNumber) {
        return numbers.stream()
                .anyMatch(number -> number.equals(bonusNumber));
    }

    public List<Integer> getLottoNumbers() {
        return numbers.stream()
                .map(Number::getNumber)
                .collect(Collectors.toList());
    }
}
