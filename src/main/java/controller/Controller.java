package controller;

import java.util.EnumMap;
import model.BenefitRate;
import model.Bonus;
import model.Lotto;
import model.LottoPurchase;
import model.Prize;
import service.LottoFactory;
import view.InputView;
import view.OutputView;

public class Controller {

    public void run() {
        LottoPurchase lottoPurchase = InputView.getPurchaseLotto();
        LottoFactory lottoFactory = LottoFactory.of(lottoPurchase.getAmount());

        OutputView.printLottoCount(lottoFactory);
        OutputView.printLottoTickets(lottoFactory);

        Lotto winningLotto = InputView.getWinningLotto();
        Bonus winningBonus = InputView.getWinningBonus(winningLotto);

        EnumMap<Prize, Integer> statistic = lottoFactory.getStatistic(winningLotto, winningBonus);

        OutputView.printStatistics(statistic);
        BenefitRate benefitRate = new BenefitRate(lottoPurchase, lottoFactory.calculateBenefit(statistic));
        OutputView.printBenefit(benefitRate);
    }
}
