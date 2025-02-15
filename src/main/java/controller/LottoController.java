package controller;

import java.util.EnumMap;
import model.Bonus;
import model.Lotto;
import model.LottoPurchase;
import model.Prize;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    public LottoController() {
    }

    public void run() {
        LottoPurchase purchaseLotto = InputView.getPurchaseLotto();
        LottoService lottoService = LottoService.of(purchaseLotto.getAmount());

        OutputView.printLottoCount(lottoService);
        OutputView.printLottoTickets(lottoService);

        Lotto winningLotto = InputView.getWinningLotto();
        Bonus winningBonus = InputView.getWinningBonus(winningLotto);

        EnumMap<Prize, Integer> statistic = lottoService.getStatistic(winningLotto, winningBonus);

        OutputView.printStatistics(statistic);
        OutputView.printBenefit(lottoService.getBenefit(statistic));
    }
}
