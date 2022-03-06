package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.LottoBuyMoney;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.service.LottoService;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public Lottos buyLotto(int inputMoney, int manualAmount, List<List<Integer>> manualNumbers) {
        return lottoService.createLottos(new LottoBuyMoney(inputMoney), manualAmount, manualNumbers);
    }

    public Map<Rank, Integer> match(Lottos lottos, List<Integer> winnerNumbers, int bonusNumber) {
        return lottoService.match(lottos, winnerNumbers, bonusNumber);
    }

    public double getProfitRate(Map<Rank, Integer> result, double inputMoney) {
        return lottoService.calculateProfitRate(result, inputMoney);
    }
}
