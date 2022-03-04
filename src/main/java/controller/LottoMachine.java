package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Money;
import domain.Statistic;
import domain.UserCount;
import domain.WinningNumber;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoMachine {

    public void start() {
        Money money = new Money(InputView.askInputMoney());
        UserCount userCount = calculateUserCount(money);

        List<Lotto> manualLottoList = InputView.askInputManualLottoNumber(userCount.getManualCount());
        Lottos lottos = generateLottos(manualLottoList, userCount);

        WinningNumber winningNumber = InputView.askInputWinningNumber();
        LottoNumber bonusBall = inputBonusBall(winningNumber);

        Statistic winningStatistics = lottos.getWinningStatistics(winningNumber, bonusBall);
        printTotalStatistic(money, winningStatistics);
    }

    private UserCount calculateUserCount(Money money) {
        int totalCount = money.generateCount();
        int manualCount = InputView.astInputManualLottoCount();
        return new UserCount(totalCount, manualCount);
    }

    private Lottos generateLottos(List<Lotto> manualLottoList, UserCount userCount) {
        Lottos lottos = Lottos.generateLottos(manualLottoList, userCount.getAutoCount());
        OutputView.printLottos(lottos, userCount);
        return lottos;
    }

    private LottoNumber inputBonusBall(WinningNumber winningNumber) {
        LottoNumber bonusBall = InputView.askInputBonusBall();
        winningNumber.checkBonusBall(bonusBall);
        return bonusBall;
    }

    private void printTotalStatistic(Money money, Statistic winningStatistics) {
        double profitRate = winningStatistics.getProfitRate(money);
        OutputView.printStatistics(winningStatistics);
        OutputView.printProfitRate(profitRate);
    }
}
