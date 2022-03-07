import controller.LottoController;
import domain.Lotto.Lottos;
import domain.Lotto.WinningLotto;
import domain.ResultStatus;
import domain.LottoCount;
import domain.Money;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();
        LottoController lottoController = new LottoController();

        Money money = lottoController.chargeMoney(inputView.inputPurchaseAmount());
        LottoCount lottoCount = lottoController.selectLottoCount(money, inputView.inputManualLottoCount());
        Lottos lottos = lottoController.purchaseLotto(lottoCount, inputView.inputManualLottoNumber(lottoCount.getManualLottoCount()));
        outputView.printPurchasedLotto(lottoCount.getManualLottoCount(), lottoCount.getAutoLottoCount(), lottos);

        WinningLotto winningLotto = lottoController.determineWinningNumber(inputView.inputWinningNumber(), inputView.inputBonusBall());
        ResultStatus resultStatus = lottoController.makeResult(lottos, winningLotto);
        outputView.printResult(resultStatus.getResultStatistics());
        outputView.printIncomeRate(lottoController.calculateImcomeRate(resultStatus, lottos));
    }
}
