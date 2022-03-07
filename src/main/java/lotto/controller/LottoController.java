package lotto.controller;

import java.util.List;
import lotto.domain.LottoBuyMoney;
import lotto.domain.Lottos;
import lotto.domain.Statistics;
import lotto.dto.request.LottoRequest;
import lotto.dto.request.StatisticsRequest;
import lotto.dto.result.LottosResult;
import lotto.dto.result.StatisticsResult;
import lotto.service.LottoService;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public LottosResult buyLotto(LottoRequest lottoRequest) {
        LottoBuyMoney lottoBuyMoney = new LottoBuyMoney(lottoRequest.getInputMoney());
        int autoLottoAmount = lottoBuyMoney.countAutoAmountByManualAmount(lottoRequest.getManualAmount());
        Lottos lottos = lottoService.createLottos(autoLottoAmount, lottoRequest.getManualLottoNumbers());
        return new LottosResult(lottos.getLottos(), autoLottoAmount, lottoRequest.getManualAmount());
    }

    public StatisticsResult match(StatisticsRequest statisticsRequest) {
        List<List<Integer>> lottos = statisticsRequest.getLottos();
        List<Integer> winnerNumbers = statisticsRequest.getWinnerNumbers();
        int bonusNumber = statisticsRequest.getBonusNumber();
        int inputMoney = statisticsRequest.getInputMoney();
        Statistics statistics = lottoService.match(lottos, winnerNumbers, bonusNumber);
        double profitRate = lottoService.getProfitRate(statistics, inputMoney);
        return new StatisticsResult(statistics.getResult(), profitRate);
    }
}
