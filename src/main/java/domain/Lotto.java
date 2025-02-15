package domain;

import domain.numbergenerator.NumberGenerator;
import dto.OutputLottosDto;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers.stream().sorted().toList();
        validate(this.numbers);
    }

    public static Lotto from(NumberGenerator numberGenerator) {
        List<Integer> numbers = numberGenerator.generateNumber();
        return new Lotto(numbers);
    }

    public boolean hasDuplicateNumber(final int bonusNumber) {
        return numbers.stream().anyMatch(number -> number == bonusNumber);
    }

    public OutputLottosDto getOutputLottoDto() {
        return new OutputLottosDto(numbers);
    }

    public boolean has(int number) {
        return this.numbers.contains(number);
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

    public void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호의 개수는 6개여야 합니다.");
        }
        if (hasDistinctNumber(numbers)) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
        if (numbers.stream().anyMatch(number -> !isValidNumber(number))) {
            throw new IllegalArgumentException("1~45 범위 이내여야 합니다.");
        }
        if (!isSorted(numbers)) {
            throw new IllegalArgumentException("로또 번호는 정렬되어야 합니다.");
        }
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
