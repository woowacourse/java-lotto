package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        LottoBuyingMoney lottoBuyingMoney = getLottoBuyingMoney();
        LottoCount lottoCount = getNumOfCustomLottos(lottoBuyingMoney);

        Lottos lottos = getLottos(lottoCount);
        OutputView.printLottos(lottos, lottoCount.custom());

        WinningStatistics winStat = new WinningStatistics(lottos.match(getWinningLotto()));
        OutputView.printStatistics(winStat.getStatistics());
        OutputView.printInterestRate(winStat.getInterestRate(lottoBuyingMoney));
    }

    private static LottoCount getNumOfCustomLottos(LottoBuyingMoney money) {
        try {
            return new LottoCount(money, InputView.getNumOfCustomLottos());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getNumOfCustomLottos(money);
        }
    }

    private static LottoBuyingMoney getLottoBuyingMoney() {
        try {
            return new LottoBuyingMoney(InputView.getLottoBuyingMoney());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottoBuyingMoney();
        }
    }

    private static Lottos getLottos(LottoCount lottoCount) {
        try {
            return LottoVendingMachine.getLottos(lottoCount, InputView.getCustomLottoNumbers(lottoCount.custom()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getLottos(lottoCount);
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
