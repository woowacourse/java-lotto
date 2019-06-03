package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        LottoBuyingMoney lottoBuyingMoney = getLottoBuyingMoney();
        LottoVendingMachine machine = getLottoVendingMachine(lottoBuyingMoney);

        int numOfCustomLottos = getNumOfCustomLottos(machine);

        Lottos lottos = getLottos(machine, numOfCustomLottos);
        OutputView.printLottos(lottos, numOfCustomLottos);

        WinningStatistics winStat = new WinningStatistics(lottos.match(getWinningLotto()));
        OutputView.printStatistics(winStat.getStatistics());
        OutputView.printInterestRate(winStat.getInterestRate(lottoBuyingMoney));
    }

    private static int getNumOfCustomLottos(LottoBuyingMoney money) {
        int numOfCustomLottos = 0;
        do {
            numOfCustomLottos = InputView.getNumOfCustomLottos();
        } while(numOfCustomLottos < 0 || numOfCustomLottos > money.numOfLottos());
    }

    private static LottoBuyingMoney getLottoBuyingMoney() {
        try {
            LottoBuyingMoney lottoBuyingMoney = new LottoBuyingMoney(InputView.getLottoBuyingMoney());
            return lottoBuyingMoney;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottoBuyingMoney();
        }
    }

    private static LottoVendingMachine getLottoVendingMachine(LottoBuyingMoney lottoBuyingMoney) {
        try {
            return new LottoVendingMachine(lottoBuyingMoney, InputView.getNumOfCustomLottos());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottoVendingMachine(lottoBuyingMoney);
        }
    }

    private static Lottos getLottos(LottoVendingMachine machine, int numOfCustomLottos) {
        try {
            return machine.getLottos(InputView.getCustomLottoNumbers(numOfCustomLottos));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottos(machine, numOfCustomLottos);
        }
    }

    private static WinningLotto getWinningLotto() {
        try {
            return new WinningLotto(InputView.getWinningNumbers(), InputView.getBonusBall());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getWinningLotto();
        }
    }
}
