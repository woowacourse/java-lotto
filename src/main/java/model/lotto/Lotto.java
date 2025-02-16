package model.lotto;

import dto.LottoNumbersResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static global.constant.ErrorMessage.LOTTO_NUMBER_DUPLICATE_MESSAGE;
import static global.utils.RandomNumber.generateRandomNumber;
import static global.utils.Validator.validateNumberRange;
import static global.utils.Validator.validateNumeric;
import static model.lotto.LottoConstant.LOTTO_LENGTH;
import static model.lotto.LottoConstant.MAX_LOTTO_NUMBER;
import static model.lotto.LottoConstant.MIN_LOTTO_NUMBER;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto() {
        numbers = generateDefaultLotto();
    }

    public Lotto(final String input) {
        numbers = new ArrayList<>();
        generateCustomLotto(input);

        validateNumberRange(numbers.size(), LOTTO_LENGTH.getValue(), LOTTO_LENGTH.getValue());
        validateDuplicateNumber(numbers);
    }

    public int calculateMatchNumber(final Lotto otherLotto) {
        return (int) numbers.stream()
                .filter(otherLotto::isContained)
                .count();
    }

    public boolean isContained(final int number) {
        return numbers.contains(number);
    }

    public LottoNumbersResponse createResponse() {
        return new LottoNumbersResponse(numbers.stream()
                .map(String::valueOf)
                .toArray(String[]::new));
    }

    private List<Integer> generateDefaultLotto() { // 구매 로또 자동 발급
        Set<Integer> uniqueNumbers = new HashSet<>();
        while (uniqueNumbers.size() < LOTTO_LENGTH.getValue()) {
            uniqueNumbers.add(generateRandomLottoNumber());
        }

        return new ArrayList<>(uniqueNumbers);
    }

    private int generateRandomLottoNumber() {
        return generateRandomNumber(MIN_LOTTO_NUMBER.getValue(), MAX_LOTTO_NUMBER.getValue());
    }

    private void generateCustomLotto(final String input) { // 지난 로또 번호 수동 발급
        String[] tokens = input.split(", ");

        Arrays.stream(tokens)
                .forEach(this::validateAndAddNumber);
    }

    private void validateAndAddNumber(final String input) {
        validateNumeric(input);
        int parsedNumber = Integer.parseInt(input);

        validateNumberRange(parsedNumber, MIN_LOTTO_NUMBER.getValue(), MAX_LOTTO_NUMBER.getValue());
        numbers.add(parsedNumber);
    }

    private void validateDuplicateNumber(final List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException(LOTTO_NUMBER_DUPLICATE_MESSAGE.getMessage());
        }
    }
}
