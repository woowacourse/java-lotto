package lotto.dto;

import lotto.domain.exceptions.ExceptionMessages;
import lotto.domain.exceptions.LottoNumberException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoDto {
    private static final String DELIMETER = ",";
    private final List<Integer> numbers;
    private final int bonus;


    public WinningLottoDto(String numbers, String bonus) {
        try {
            this.numbers = parseNumber(numbers);
            this.bonus = Integer.parseInt(bonus);
        } catch (NumberFormatException e) {
            throw new LottoNumberException(ExceptionMessages.NUMBER.message());
        }
    }

    private List<Integer> parseNumber(String text) {
        return Arrays.stream(text.split(DELIMETER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonus() {
        return bonus;
    }
}
