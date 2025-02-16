import controller.LottoController;
import domain.BonusNumber;
import domain.LottoBuyResultFormatter;
import domain.LottoDispenser;
import domain.WinningCalculateFormatter;
import domain.WinningNumber;
import exception.LottoException;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class Application {

    private static final LottoController lottoController = new LottoController(new LottoService(
            new WinningCalculateFormatter(), new LottoBuyResultFormatter()));

    public static void main(String[] args) {
        startLotto();
    }

    private static void startLotto() {
        LottoDispenser lottoDispenser = buyLotto();
        WinningNumber winningNumber = generateWinningNumber();
        BonusNumber bonusNumber = generateBonusNumbers();
        printWinningResult(lottoDispenser, winningNumber, bonusNumber);
    }

    private static LottoDispenser buyLotto() {
        LottoDispenser lottoDispenser = null;
        while (lottoDispenser == null) {
            lottoDispenser = buyLottoInput();
        }
        OutputView.printBuyLotto(lottoController.formattingBuyLottoResult(lottoDispenser));
        return lottoDispenser;
    }

    private static LottoDispenser buyLottoInput() {
        try {
            String inputBuyLottoMoney = InputView.inputBuyLottoMoney();
            return lottoController.inputBuyLottoMoney(inputBuyLottoMoney);
        } catch (LottoException lottoException) {
            OutputView.printError(lottoException);
            return null;
        }
    }

    private static WinningNumber generateWinningNumber() {
        WinningNumber winningNumber = null;
        while (winningNumber == null) {
            winningNumber = inputWinningNumber();
        }
        return winningNumber;
    }

    private static WinningNumber inputWinningNumber() {
        try {
            String inputWinningNumber = InputView.inputWinningNumber();
            return lottoController.inputWinningNumber(inputWinningNumber);
        } catch (LottoException lottoException) {
            OutputView.printError(lottoException);
            return null;
        }
    }

    private static BonusNumber generateBonusNumbers() {
        BonusNumber bonusNumber = null;
        while (bonusNumber == null) {
            bonusNumber = inputBonusNumber();
        }
        return bonusNumber;
    }

    private static BonusNumber inputBonusNumber() {
        try {
            String inputBonusNumber = InputView.inputBonusNumber();
            return lottoController.inputBonusNumber(inputBonusNumber);
        } catch (LottoException lottoException) {
            OutputView.printError(lottoException);
            return null;
        }
    }

    private static void printWinningResult(LottoDispenser lottoDispenser, WinningNumber winningNumber,
                                           BonusNumber bonusNumber) {
        System.out.println(lottoController.formattingWinningResult(lottoDispenser, winningNumber, bonusNumber));
    }
}
