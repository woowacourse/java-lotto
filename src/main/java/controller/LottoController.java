package controller;

import java.util.EnumMap;
import model.Bonus;
import model.Lotto;
import model.LottoPurchase;
import model.Prize;
import model.LottoFactory;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        LottoPurchase lottoPurchase = getLottoPurchase();
        Lotto winningLotto = getWinningLotto();
        Bonus winningBonus = getWinningBonus(winningLotto);

        LottoFactory lottoFactory = LottoFactory.of(lottoPurchase);
        EnumMap<Prize, Integer> prizes = lottoFactory.getStatistic(winningLotto, winningBonus);

        printLotto(lottoFactory);
        printStatistics(prizes, lottoFactory);
    }

    private LottoPurchase getLottoPurchase() {
        OutputView.printLottoPurchaseGuidance();
        return InputView.getPurchaseLotto();
    }

    private Lotto getWinningLotto() {
        OutputView.printWinningLottoGuidance();
        return InputView.getWinningLotto();
    }

    private static Bonus getWinningBonus(Lotto winningLotto) {
        OutputView.printWinningBonusGuidance();
        return InputView.getWinningBonus(winningLotto);
    }

    private void printLotto(LottoFactory lottoFactory) {
        OutputView.printLottoCount(lottoFactory);
        OutputView.printLottoTickets(lottoFactory);
    }

    private void printStatistics(EnumMap<Prize, Integer> prizes, LottoFactory lottoFactory) {
        OutputView.printStatistics(prizes);
        OutputView.printBenefit(lottoFactory, prizes);
    }
}
