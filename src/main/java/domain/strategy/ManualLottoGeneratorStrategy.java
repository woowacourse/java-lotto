package domain.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoPurchaseInfo;

public class ManualLottoGeneratorStrategy implements LottoGeneratorStrategy {

    @Override
    public List<Lotto> generate(LottoPurchaseInfo lottoPurchaseInfo) {
        final List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> lottoNumber : lottoPurchaseInfo.getLottoNumbers()) {
            lottos.add(generateLotto(lottoNumber));
        }

        return lottos;
    }

    private Lotto generateLotto(List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers.stream()
            .map(LottoNumber::valueOf)
            .sorted()
            .collect(Collectors.toList()));
    }
}
