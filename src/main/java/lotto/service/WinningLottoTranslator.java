package lotto.presentation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoPresentation {
    private static final String DELIMITER = ",";
    private List<Integer> numbers;
    private int bonus;

    public WinningLottoPresentation(String numbers, String bonus) {
        this.numbers = parseLottoNumber(numbers);
        this.bonus = toInt(bonus);
    }

    private int toInt(String numberString) {
        return Integer.parseInt(numberString);
    }

    private List<Integer> parseLottoNumber(String number) {
        return Arrays.stream(number.split(DELIMITER)).map(Integer::parseInt).collect(Collectors.toList());
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonus() {
        return bonus;
    }
}
