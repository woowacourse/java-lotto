package lotto.dto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.vo.LottoNumber;

public class LottosResult {

    private final List<List<Integer>> lottos;
    private final int autoLottoAmount;
    private final int manualAmount;

    public LottosResult(List<Lotto> lottos, int autoLottoAmount, int manualAmount) {
        this.lottos = lottosToCollectNumbers(lottos);
        this.autoLottoAmount = autoLottoAmount;
        this.manualAmount = manualAmount;
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

    public int getAutoLottoAmount() {
        return autoLottoAmount;
    }

    public int getManualAmount() {
        return manualAmount;
    }
}
