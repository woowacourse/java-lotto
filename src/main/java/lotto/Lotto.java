package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    private Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto fromGenerator(LottoNumberGenerator generator) {
        return new Lotto(mapIntToLottoNumber(generator));
    }

    private static List<LottoNumber> mapIntToLottoNumber(LottoNumberGenerator generator) {
        return generator.generateNumbers()
                        .stream()
                        .map(LottoNumber::from)
                        .collect(Collectors.toList());
    }
}
