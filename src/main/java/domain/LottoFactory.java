package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    private LottoFactory() {
    }

    public static List<Lotto> createLottos(LottoCount LottoCount, LottoNumbersGenerator lottoNumbersGenerator) {
        return IntStream.range(0, LottoCount.getAutoLottoCount()).boxed()
            .map(i -> createLotto(lottoNumbersGenerator))
            .collect(Collectors.toList());
    }

    public static Lotto createLotto(LottoNumbersGenerator lottoNumbersGenerator) {
        List<LottoNumber> lottoNumbers = lottoNumbersGenerator.generate();

        return new Lotto(lottoNumbers);
    }

    public static List<Lotto> createLottos(List<List<Integer>> numberLists) {
        return numberLists.stream()
            .map(LottoFactory::createLotto)
            .collect(Collectors.toList());
    }

    public static Lotto createLotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }
}
