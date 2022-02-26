
public class LottoMachine {

    private Money money;
    private Lottos lottos;
    private WinningNumber winningNumber;

    public void start() {
        int lottoCount = inputMoney();
        lottos = Lottos.generateLottos(lottoCount);
        OutputView.printLottos(lottos);
        LottoNumber bonusBall = inputWinningNumber();
        getStatistics(bonusBall);
    }

    private int inputMoney() {
        money = new Money(InputView.askInputMoney());
        int count = money.generateCount();
        OutputView.printCountOfLotto(count);
        return count;
    }

    private LottoNumber inputWinningNumber() {
        winningNumber = InputView.askInputWinningNumber();
        LottoNumber bonusBall = InputView.askInputBonusBall();
        winningNumber.checkBonusBall(bonusBall);
        return bonusBall;
    }

    private void getStatistics(LottoNumber bonusBall) {
        Statistic winningStatistics = lottos.getWinningStatistics(winningNumber, bonusBall);
        OutputView.printStatistics(winningStatistics);
        OutputView.printProfitRate(winningStatistics, money);
    }
}
