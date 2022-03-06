package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    private LottoFactory() {
    }

    public static List<Lotto> createLottos(LottoCount LottoCount, List<List<Integer>> manualLottoNumbers,
        LottoNumbersGenerator autoLottoNumbersGenerator) {
        List<Lotto> lottos = manualLottoNumbers.stream()
            .map(LottoFactory::createLotto)
            .collect(Collectors.toList());
        lottos.addAll(IntStream.range(0, LottoCount.autoLottoCount()).boxed()
            .map(i -> createLotto(autoLottoNumbersGenerator))
            .collect(Collectors.toList()));
        return lottos;
    }

    public static Lotto createLotto(LottoNumbersGenerator lottoNumbersGenerator) {
        return new Lotto(lottoNumbersGenerator.generate());
    }

    public static Lotto createLotto(List<Integer> numbers) {
        return new Lotto(numbers.stream()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList()));
    }
}
