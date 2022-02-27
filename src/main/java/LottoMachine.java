
public class LottoMachine {

    public void start() {
        Money money = new Money(InputView.askInputMoney());
        int lottoCount = calculateCount(money);
        Lottos lottos = getnerateLottos(lottoCount);

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

    private Lottos getnerateLottos(int lottoCount) {
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
