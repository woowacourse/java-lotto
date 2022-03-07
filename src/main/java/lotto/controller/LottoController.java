package lotto.controller;

import java.util.List;
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
        int inputMoney = lottoRequest.getInputMoney();
        List<List<Integer>> manualLottoNumbers = lottoRequest.getManualLottoNumbers();

        int autoLottoAmount = lottoService.countAutoLottos(inputMoney, manualLottoNumbers.size());
        Lottos lottos = lottoService.createLottos(autoLottoAmount, manualLottoNumbers);
        return new LottosResult(lottos.getLottos(), autoLottoAmount, manualLottoNumbers.size());
    }

    public StatisticsResult match(StatisticsRequest statisticsRequest) {
        List<List<Integer>> lottoNumbers = statisticsRequest.getLottoNumbers();
        List<Integer> winnerNumbers = statisticsRequest.getWinnerNumbers();
        int bonusNumber = statisticsRequest.getBonusNumber();
        int inputMoney = statisticsRequest.getInputMoney();

        Statistics statistics = lottoService.match(lottoNumbers, winnerNumbers, bonusNumber);
        double profitRate = lottoService.getProfitRate(statistics, inputMoney);
        return new StatisticsResult(statistics.getResult(), profitRate);
    }
}
