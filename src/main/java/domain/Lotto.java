package domain;

import domain.numbergenerator.NumberGenerator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Lotto {

    public static final int LOTTO_PRICE = 1000;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MAX_LOTTO_SIZE = 6;

    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto issueByNumberGenerator(NumberGenerator numberGenerator) {
        List<Integer> lottoNumbers = new ArrayList<>();

        while (lottoNumbers.size() < MAX_LOTTO_SIZE) {
            int number = numberGenerator.generate();

            addNumberIfUnique(lottoNumbers, number);
        }

        lottoNumbers.sort(Comparator.naturalOrder());

        return new Lotto(lottoNumbers);
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

    private static void addNumberIfUnique(List<Integer> lottoNumbers, int number) {
        if (!lottoNumbers.contains(number)) {
            lottoNumbers.add(number);
        }
    }

}
