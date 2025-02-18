package controller;

import java.util.EnumMap;
import model.BenefitRate;
import model.Bonus;
import model.Lotto;
import model.LottoPurchase;
import model.Prize;
import model.WinningLotto;
import service.LottoFactory;
import view.InputView;
import view.OutputView;

public class Controller {

    public void run() {
        LottoPurchase lottoPurchase = InputView.getPurchaseLotto();
        LottoFactory lottoFactory = LottoFactory.of(lottoPurchase.amount());

        OutputView.printLottoCount(lottoFactory);
        OutputView.printLottoTickets(lottoFactory);

        Lotto lotto = InputView.getWinningLotto();
        Bonus bonus = InputView.getWinningBonus(lotto);
        WinningLotto winningLotto = new WinningLotto(lotto, bonus);

        EnumMap<Prize, Integer> statistic = lottoFactory.getStatistic(winningLotto);

        OutputView.printStatistics(statistic);
        BenefitRate benefitRate = new BenefitRate(lottoPurchase, lottoFactory.calculateBenefit(statistic));
        OutputView.printBenefit(benefitRate);
    }
}
