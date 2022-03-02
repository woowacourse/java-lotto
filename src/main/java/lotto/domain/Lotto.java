package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.validator.LottoValidator;

public class Lotto {

    public static final long LOTTO_PRICE = 1000;

    private static final String INPUT_NUMBERS_DELIMITER = ",";

    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        LottoValidator.validate(lottoNumbers);
        this.lottoNumbers = List.copyOf(lottoNumbers);
    }

    public static Lotto generateLottoByString(String lottoNumbers) {
        return new Lotto(receiveStringInput(lottoNumbers));
    }

    private static List<LottoNumber> receiveStringInput(String stringInput) {
        return convertToLottoNumbers(parseStringInput(stringInput));
    }

    private static List<Integer> parseStringInput(String stringInput) {
        return Arrays.stream(stringInput.split(INPUT_NUMBERS_DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::findByNumber)
                .sorted()
                .collect(Collectors.toList());
    }

    public static Lotto generateLottoByAuto() {
        return new Lotto(LottoNumber.getRandomLottoNumbers());
    }

    public int getMatchCount(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::isContain)
                .count();
    }

    public boolean isContain(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", "));
    }
}
