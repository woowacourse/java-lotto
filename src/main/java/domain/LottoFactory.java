package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.strategy.LottoGeneratorStrategy;
import domain.strategy.ManualLottoGeneratorStrategy;
import domain.strategy.AutomaticLottoGeneratorStrategy;

public class LottoFactory {

    private final List<LottoGeneratorStrategy> lottoGeneratorStrategies;

    public LottoFactory() {
        this.lottoGeneratorStrategies = new ArrayList<>(
            Arrays.asList(new ManualLottoGeneratorStrategy(), new AutomaticLottoGeneratorStrategy()));
    }

    public Lottos generateLottos(List<List<Integer>> lottosNumbers, LottoPurchaseCount lottoPurchaseCount) {
        final LottoPurchaseInfo purchaseInfo = new LottoPurchaseInfo(lottosNumbers, lottoPurchaseCount);
        final List<Lotto> lottos = new ArrayList<>();

        for (LottoGeneratorStrategy lottoGeneratorStrategy : lottoGeneratorStrategies) {
            lottos.addAll(lottoGeneratorStrategy.generate(purchaseInfo));
        }

        return new Lottos(lottos);
    }

    public Lotto createLotto(List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers.stream()
            .map(LottoNumber::valueOf)
            .sorted()
            .collect(Collectors.toList()));
    }
}