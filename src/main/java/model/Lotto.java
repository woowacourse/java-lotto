package model;

import dto.LottoNumbersResponse;
import global.utils.Validator;

import java.util.*;

import static global.constant.LottoConstant.*;

public class Lotto {

    private static final String NUMBER_DELIMITER = ", ";

    private final List<Integer> numbers;

    public Lotto() {
        numbers = new ArrayList<>(generateLotto());
    }

    public Lotto(final String input) {
        numbers = new ArrayList<>(generateCustomLotto(input));
    }

    public int calculateMatchNumber(final Lotto otherLotto) {
        return (int) numbers.stream().filter(otherLotto::isContained).count();
    }

    public boolean isContained(final int number) {
        return numbers.contains(number);
    }

    public LottoNumbersResponse createResponse() {
        return new LottoNumbersResponse(numbers.stream()
                .map(String::valueOf)
                .toArray(String[]::new)
        );
    }

    private List<Integer> generateLotto() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < NUMBER_COUNT; i++) {
            numbers.add(random.nextInt(MAX_LOTTO_NUMBER) + 1);
        }

        return numbers;
    }

    private List<Integer> generateCustomLotto(final String input) {
        List<Integer> numbers = new ArrayList<>();

        String[] tokens = input.split(NUMBER_DELIMITER);
        Validator.validateRange(tokens.length, NUMBER_COUNT, NUMBER_COUNT);

        Arrays.stream(tokens).
                forEach(t -> numbers.add(convertNumber(t)));

        validateUniqueNumber(numbers);

        return numbers;
    }

    private int convertNumber(String token) {
        Validator.validateNumeric(token);
        int number = Integer.parseInt(token);
        Validator.validateRange(number, MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER);
        return number;
    }

    private void validateUniqueNumber(final List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
}
