package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        Money lottoBuyingMoney = getLottoBuyingMoney();
        LottoVendingMachine machine = getLottoVendingMachine(lottoBuyingMoney);

        int numOfCustomLottos = getNumOfCustomLottos(machine);

        Lottos lottos = getLottos(machine, numOfCustomLottos);
        OutputView.printLottos(lottos, numOfCustomLottos);

        WinningStatistics winStat = new WinningStatistics(lottos.match(getWinningLotto()));
        OutputView.printStatistics(winStat.getStatistics());
        OutputView.printInterestRate(winStat.getInterestRate(lottoBuyingMoney));
    }

    private static Money getLottoBuyingMoney() {
        try {
            Money lottoBuyingMoney = new Money(InputView.getLottoBuyingMoney());
            LottoVendingMachine.validateMoney(lottoBuyingMoney);
            return lottoBuyingMoney;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottoBuyingMoney();
        }
    }

    private static LottoVendingMachine getLottoVendingMachine(Money money) {
        try {
            return new LottoVendingMachine(money);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottoVendingMachine(money);
        }
    }

    private static int getNumOfCustomLottos(LottoVendingMachine machine) {
        try {
            int numOfCustomLottos = InputView.getNumOfCustomLottos();
            machine.validateNumOfCustomLottos(numOfCustomLottos);
            return numOfCustomLottos;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getNumOfCustomLottos(machine);
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
