package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.vo.LottoNumber;

public class LottosResult {

    private List<List<Integer>> lottos;

    public LottosResult(List<Lotto> lottos) {
        this.lottos = lottosToCollectNumbers(lottos);
    }

    private List<List<Integer>> lottosToCollectNumbers(List<Lotto> lottos) {
        return lottos.stream()
            .map(this::lottoToNumbers)
            .collect(Collectors.toList());
    }

    private List<Integer> lottoToNumbers(Lotto lotto) {
        return lotto.getNumbers().stream()
            .map(LottoNumber::getNumber)
            .collect(Collectors.toList());
    }

    public List<List<Integer>> getLottos() {
        return lottos;
    }
}
