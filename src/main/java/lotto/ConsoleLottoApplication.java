package lotto;

import lotto.domain.*;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        LottoBuyingMoney lottoBuyingMoney = getLottoBuyingMoney();
        LottoCount lottoCount = getNumOfCustomLottos(lottoBuyingMoney);

        Lottos lottos = getLottos(lottoCount);
        ConsoleOutputView.printLottos(lottos, lottoCount.custom());

        WinningStatistics winStat = new WinningStatistics(lottos.match(getWinningLotto()));
        ConsoleOutputView.printStatistics(winStat.getStatistics());
        ConsoleOutputView.printInterestRate(winStat.getInterestRate(lottoBuyingMoney));
    }

    private static LottoCount getNumOfCustomLottos(LottoBuyingMoney money) {
        try {
            return new LottoCount(money, ConsoleInputView.getNumOfCustomLottos());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getNumOfCustomLottos(money);
        }
    }

    private static LottoBuyingMoney getLottoBuyingMoney() {
        try {
            return new LottoBuyingMoney(ConsoleInputView.getLottoBuyingMoney());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottoBuyingMoney();
        }
    }

    private static Lottos getLottos(LottoCount lottoCount) {
        try {
            return LottoVendingMachine.getLottos(lottoCount, ConsoleInputView.getCustomLottoNumbers(lottoCount.custom()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottos(lottoCount);
        }
    }

    private static WinningLotto getWinningLotto() {
        try {
            return new WinningLotto(ConsoleInputView.getWinningNumbers(), ConsoleInputView.getBonusBall());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getWinningLotto();
        }
    }
}
