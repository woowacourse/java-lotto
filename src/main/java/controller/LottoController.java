package controller;

import controller.dto.LottosDto;
import controller.dto.StatisticDto;
import domain.*;
import service.LottoService;

import java.util.List;

public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    public LottosDto purchase(int inputMoney, int manualLottoCount, List<String[]> manualLottoNumbers) {
        Money money = lottoService.createMoney(inputMoney);
        int autoLottoCount = lottoService.getAutoLottoCount(money, manualLottoCount);
        Lottos lottos = lottoService.generateLottos(manualLottoNumbers, autoLottoCount);

        return LottosDto.from(lottos, lottos.size());
    }

    public StatisticDto winningResult(String[] inputWinningNumber,
                                      int inputBonusBall,
                                      List<String[]> manualLottoNumbers,
                                      int inputMoney) {
        WinningLotto winningLotto = lottoService.generateWinningLotto(
                new ManualLottoGenerator(inputWinningNumber),
                inputBonusBall);

        Lottos lottos = lottoService.generateLottos(manualLottoNumbers, 0);
        Statistic winningStatistics = lottos.getWinningStatistics(winningLotto);
        Money money = lottoService.createMoney(inputMoney);
        return StatisticDto.from(winningStatistics, winningStatistics.getProfitRate(money));
    }
}
