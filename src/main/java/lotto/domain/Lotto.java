package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.validator.LottoValidator;

public class Lotto {

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

    public int getMatchCount(Lotto winningLotto) {
        return (int) lottoNumbers.stream()
                .filter(winningLotto::isContain)
                .count();
    }

    public boolean isContain(LottoNumber bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    @Override
    public String toString() {
        return lottoNumbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", "));
    }
}
