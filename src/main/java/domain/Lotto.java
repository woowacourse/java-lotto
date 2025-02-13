package domain;

import domain.numbergenerator.NumberGenerator;
import dto.OutputLottosDto;
import java.util.List;
import validator.LottoValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers.stream().sorted().toList();
        LottoValidator.validate(this.numbers);
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
}
