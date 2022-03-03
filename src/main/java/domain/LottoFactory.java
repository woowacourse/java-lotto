package domain;

import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

    private LottoFactory() {
    }

    public static Lotto createLotto(LottoNumbersGenerator lottoNumbersGenerator) {
        List<LottoNumber> lottoNumbers = lottoNumbersGenerator.generate();

        return new Lotto(lottoNumbers);
    }

    public static Lotto createLotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }
}
