package domain;

import domain.numbergenerator.NumberGenerator;
import dto.OutputLottosDto;
import java.util.List;
import message.ErrorMessage;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers.stream().sorted().toList();
        validate(this.numbers);
    }

    public void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_COUNT.getMessage());
        }
        if (hasDistinctNumber(numbers)) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_DUPLICATED.getMessage());
        }
        if (numbers.stream().anyMatch(number -> !isValidNumber(number))) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_RANGE.getMessage());
        }
        if (!isSorted(numbers)) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NOT_SORTED.getMessage());
        }
    }

    public static Lotto from(NumberGenerator numberGenerator) {
        List<Integer> numbers = numberGenerator.generateNumber();
        return new Lotto(numbers);
    }

    public boolean has(int number) {
        return this.numbers.contains(number);
    }

    public OutputLottosDto getOutputLottoDto() {
        return new OutputLottosDto(numbers);
    }

    public int getHitCountFrom(Lotto lotto) {
        int hitCount = 0;
        for (Integer number : this.numbers) {
            hitCount += isHit(lotto.numbers, number);
        }
        return hitCount;
    }

    private int isHit(List<Integer> numbers, int number) {
        if (numbers.contains(number)) {
            return 1;
        }
        return 0;
    }

    private boolean isSorted(List<Integer> numbers) {
        List<Integer> sortedNumbers = numbers.stream().sorted().toList();
        return sortedNumbers.equals(numbers);
    }

    private boolean isValidNumber(int number) {
        return number > 0 && number <= 45;
    }

    private boolean hasDistinctNumber(List<Integer> numbers) {
        int distinctedSize = numbers.stream()
                .distinct()
                .toList()
                .size();

        return numbers.size() != distinctedSize;
    }
}
