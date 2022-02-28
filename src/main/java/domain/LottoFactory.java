package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.strategy.LottoGeneratorStrategy;

public class LottoFactory {

    private LottoFactory() {
    }

    public static Lottos generateLottos(List<List<Integer>> lottosNumbers, LottoPurchaseCount lottoPurchaseCount,
        LottoGeneratorStrategy lottoGeneratorStrategy) {

        final List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(createManualLottos(lottosNumbers));
        lottos.addAll(createAutomaticLottos(lottoPurchaseCount.getAutomaticCount(), lottoGeneratorStrategy));

        return new Lottos(lottos);
    }

    public static Lotto createLotto(List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers.stream()
            .map(LottoNumber::valueOf)
            .collect(Collectors.toList()));
    }

    private static List<Lotto> createManualLottos(List<List<Integer>> lottoNumbers) {
        return lottoNumbers.stream()
            .map(lottoNumber -> createLotto(lottoNumber))
            .collect(Collectors.toList());
    }

    private static List<Lotto> createAutomaticLottos(int automaticCount, LottoGeneratorStrategy lottoGeneratorStrategy) {
        return IntStream.range(0, automaticCount)
            .mapToObj(count -> lottoGeneratorStrategy.generate())
            .map(lottoNumbers -> new Lotto(lottoNumbers))
            .collect(Collectors.toList());
    }
}
