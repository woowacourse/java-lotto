package model;

import dto.LottoNumbersResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import utils.Validator;

public class Lotto {

    private static final int NUMBER_COUNT = 6;

    private final List<Integer> numbers;

    public Lotto() { // todo : 메서드 분리 작업
        numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < NUMBER_COUNT; i++) {
            numbers.add(random.nextInt(45) + 1);
        }
    }

    public Lotto(String input) { // todo : 메서드 분리 작업, Stream 적용 작업
        numbers = new ArrayList<>();
        String[] token = input.split(", ");
        Validator.validateRange(token.length, NUMBER_COUNT, NUMBER_COUNT);
        for (int i = 0; i < token.length; i++) {
            Validator.validateNumeric(token[i]);
            numbers.add(Integer.parseInt(token[i]));
        }
        validateUniqueNumber(numbers);
    }

    public boolean isContained(int number) {
        return numbers.contains(number);
    }

    public LottoNumbersResponse createResponse() {
        return new LottoNumbersResponse(
                numbers.stream()
                        .map(String::valueOf)
                        .toArray(String[]::new)
        );
    }

    private void validateUniqueNumber(List<Integer> numbers) { // todo : 에러 메시지 추가
        Set<Integer> set = new HashSet<>(numbers);
        if (set.size() != numbers.size()) {
            throw new IllegalArgumentException();
        }
    }
}
