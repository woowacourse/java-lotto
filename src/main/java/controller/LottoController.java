package controller;

import java.util.EnumMap;
import model.Bonus;
import model.Lotto;
import model.LottoPurchase;
import model.Prize;
import service.LottoFactory;
import view.InputView;
import view.OutputView;

public class LottoController {

    public LottoController() {
    }

    public void run() {
        LottoPurchase purchaseLotto = InputView.getPurchaseLotto();
        LottoFactory lottoFactory = LottoFactory.of(purchaseLotto.getAmount());

        OutputView.printLottoCount(lottoFactory);
        OutputView.printLottoTickets(lottoFactory);

        Lotto winningLotto = InputView.getWinningLotto();
        Bonus winningBonus = InputView.getWinningBonus(winningLotto);

        EnumMap<Prize, Integer> statistic = lottoFactory.getStatistic(winningLotto, winningBonus);

        OutputView.printStatistics(statistic);
        OutputView.printBenefit(lottoFactory.getBenefit(statistic));
    }
}
