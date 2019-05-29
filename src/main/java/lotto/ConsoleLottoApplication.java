package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleLottoApplication {
    private static int lottoBuyingMoney;
    private static int numOfCustomLottos;
    private static LottoVendingMachine lottoVendingMachine;

    public static void main(String[] args) {
        lottoVendingMachine = getLottoVendingMachine();
        Lottos lottos = getLottos();
        OutputView.printLottos(lottos, numOfCustomLottos);
        WinningLotto winLotto = getWinningLotto();
        WinningStatistics winStat = new WinningStatistics(lottos.match(winLotto));
        OutputView.printStatistics(winStat.getStatistics());
        OutputView.printInterestRate(winStat.getInterestRate());
    }

    private static LottoVendingMachine getLottoVendingMachine() {
        try {
            lottoBuyingMoney = InputView.getLottoBuyingMoney();
            numOfCustomLottos = InputView.getNumOfCustomLottos();
            return new LottoVendingMachine(lottoBuyingMoney, numOfCustomLottos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottoVendingMachine();
        }
    }

    private static WinningLotto getWinningLotto() {
        try {
            List<Integer> winningNumbers = InputView.getWinningNumbers();
            int bonusBall = InputView.getBonusBall();
            return new WinningLotto(winningNumbers, bonusBall);
        } catch (Exception e) {
            return getWinningLotto();
        }
    }

    private static Lottos getLottos() {
        try {
            return lottoVendingMachine.getLottos(InputView.getCustomLottoNumbers(numOfCustomLottos));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottos();
        }
    }
}
