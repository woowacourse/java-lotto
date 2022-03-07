package lotto.controller;

import java.util.List;
import lotto.domain.LottoBuyMoney;
import lotto.domain.Lottos;
import lotto.domain.Statistics;
import lotto.dto.LottosResult;
import lotto.service.LottoService;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public LottosResult buyLotto(int inputMoney, int manualAmount, List<List<Integer>> manualNumbers) {
        LottoBuyMoney lottoBuyMoney = new LottoBuyMoney(inputMoney);
        int autoLottoAmount = lottoBuyMoney.countAutoAmountByManualAmount(manualAmount);
        Lottos lottos = lottoService.createLottos(autoLottoAmount, manualNumbers);
        return new LottosResult(lottos.getLottos(), autoLottoAmount, manualAmount);
    }

    public Statistics match(List<List<Integer>> lottos, List<Integer> winnerNumbers, int bonusNumber) {
        return lottoService.match(lottos, winnerNumbers, bonusNumber);
    }

    public double getProfitRate(Statistics statistics, int inputMoney) {
        return lottoService.getProfitRate(statistics, inputMoney);
    }
}
