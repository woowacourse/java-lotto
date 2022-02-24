package lotto.view;

import static java.util.stream.Collectors.toList;

import java.util.List;
import lotto.model.Lotto;

public class LottoParser extends Parser<Lotto> {

    static final String INVALID_LOTTO_NUMBER_FORMAT_MESSAGE = "당첨 번호의 형식이 잘못 되었습니다. 예) 1, 2, 3, 4, 5, 6";

    @Override
    protected Lotto convert(String text) {
        List<String> splitNumbers = splitNumbers(text);
        List<String> trimNumbers = trimNumbers(splitNumbers);
        return new Lotto(toInts(trimNumbers));
    }

    private List<String> splitNumbers(String numbers) {
        return List.of(numbers.split(","));
    }

    private List<String> trimNumbers(List<String> numbers) {
        return numbers.stream()
            .map(String::trim)
            .collect(toList());
    }

    private List<Integer> toInts(List<String> numbers) {
        return numbers.stream()
            .map(Integer::valueOf)
            .collect(toList());
    }

    @Override
    protected String regex() {
        return "^" + numberWithSpacesRegex() + "(\\s*," + numberWithSpacesRegex() + "){5}$";
    }

    @Override
    protected String errorMessage() {
        return INVALID_LOTTO_NUMBER_FORMAT_MESSAGE;
    }
}
