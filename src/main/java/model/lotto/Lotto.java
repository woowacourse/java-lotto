package model.lotto;

import dto.LottoNumbersResponse;
import global.utils.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static model.lotto.LottoConstant.*;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto() {
        numbers = new ArrayList<>();
        generateLotto();
    }

    public Lotto(final String input) {
        numbers = new ArrayList<>();
        generateCustomLotto(input);
    }

    public int calculateMatchNumber(final Lotto otherLotto) {
        return (int) numbers.stream().filter(otherLotto::isContained).count();
    }

    public boolean isContained(final int number) {
        return numbers.contains(number);
    }

    public LottoNumbersResponse createResponse() {
        return new LottoNumbersResponse(numbers.stream().map(String::valueOf).toArray(String[]::new));
    }

    private void generateLotto() {
        Random random = new Random();
        for (int i = 0; i < NUMBER_COUNT.getValue(); i++) {
            numbers.add(random.nextInt(MAX_LOTTO_NUMBER.getValue()) + 1);
        }
    }

    private void generateCustomLotto(final String input) {
        String[] tokens = input.split(", ");
        Validator.validateNumberRange(tokens.length, NUMBER_COUNT.getValue(), NUMBER_COUNT.getValue());

        Arrays.stream(tokens).forEach(this::addNumber);

        validateUniqueNumber(numbers);
    }

    private void addNumber(String token) {
        Validator.validateNumeric(token);
        int number = Integer.parseInt(token);
        Validator.validateNumberRange(number, MIN_LOTTO_NUMBER.getValue(), MAX_LOTTO_NUMBER.getValue());
        numbers.add(number);
    }

    private void validateUniqueNumber(final List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }
}
