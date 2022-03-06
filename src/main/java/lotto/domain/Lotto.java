package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import lotto.validator.LottoValidator;

public class Lotto {

    public static final long LOTTO_PRICE = 1000;

    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        LottoValidator.validate(lottoNumbers);
        this.lottoNumbers = List.copyOf(lottoNumbers);
    }

    public static Lotto generateByManual(List<Integer> lottoNumbers) {
        return new Lotto(convertToLottoNumbers(lottoNumbers));
    }

    private static List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::findByNumber)
                .sorted()
                .collect(Collectors.toList());
    }

    public static Lotto generateByAuto() {
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
        return lottoNumbers.toString();
    }
}
