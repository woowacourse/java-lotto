package lotto.controller;

import java.util.List;
import lotto.domain.LottoBuyMoney;
import lotto.domain.Lottos;
import lotto.domain.Statistics;
import lotto.dto.LottosResult;
import lotto.dto.StatisticsResult;
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

    public StatisticsResult match(List<List<Integer>> lottos, List<Integer> winnerNumbers, int bonusNumber, int inputMoney) {
        Statistics statistics = lottoService.match(lottos, winnerNumbers, bonusNumber);
        double profitRate = lottoService.getProfitRate(statistics, inputMoney);
        return new StatisticsResult(statistics.getResult(), profitRate);
    }
}
