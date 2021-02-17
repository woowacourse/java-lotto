package lotto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_LENGTH = 6;

    private final Set<LottoNumber> lottoNumbers;

    private Lotto(Set<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto fromGenerator(LottoNumberGenerator generator) {
        List<Integer> numbers = generator.generateNumbers();

        if (!matchesLength(numbers.size())) {
            throw new IllegalArgumentException();
        }

        return new Lotto(mapIntToLottoNumber(numbers));
    }

    private static boolean matchesLength(int size) {
        return size == LOTTO_LENGTH;
    }

    private static Set<LottoNumber> mapIntToLottoNumber(List<Integer> numbers) {
        return numbers.stream()
                      .map(LottoNumber::from)
                      .collect(Collectors.toSet());
    }
}
