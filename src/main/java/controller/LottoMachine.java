package controller;

import domain.LottoNumber;
import domain.Lottos;
import domain.Money;
import domain.Statistic;
import domain.WinningNumber;
import view.InputView;
import view.OutputView;

public class LottoMachine {

    public void start() {
        Money money = new Money(InputView.askInputMoney());
        int lottoCount = calculateCount(money);
        Lottos lottos = generateLottos(lottoCount);

        WinningNumber winningNumber = InputView.askInputWinningNumber();
        LottoNumber bonusBall = inputBonusBall(winningNumber);

        Statistic winningStatistics = lottos.getWinningStatistics(winningNumber, bonusBall);
        printTotalStatistic(money, winningStatistics);
    }

    private int calculateCount(Money money) {
        int lottoCount = money.generateCount();
        OutputView.printCountOfLotto(lottoCount);
        return lottoCount;
    }

    private Lottos generateLottos(int lottoCount) {
        Lottos lottos = Lottos.generateLottos(lottoCount);
        OutputView.printLottos(lottos);
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
