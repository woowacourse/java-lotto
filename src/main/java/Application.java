import controller.LottoController;
import exception.LottoException;
import repository.BonusNumberRepository;
import repository.LottoRepository;
import repository.WinningNumberRepository;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class Application {

    private static final LottoController lottoController = new LottoController(
            new LottoService(new LottoRepository(), new WinningNumberRepository(), new BonusNumberRepository()));

    public static void main(String[] args) {
        startLotto();
    }

    private static void startLotto() {
        buyLotto();
        settingWinningNumbers();
        settingBonusNumbers();
        printWinningResult();
    }

    private static void buyLotto() {
        boolean retry = true;
        while (retry) {
            retry = buyLottoInput();
        }
        OutputView.printBuyLotto(lottoController.formattingBuyLottoResult());
    }

    private static boolean buyLottoInput() {
        try {
            String inputBuyLottoMoney = InputView.inputBuyLottoMoney();
            lottoController.inputBuyLottoMoney(inputBuyLottoMoney);
        } catch (LottoException lottoException) {
            OutputView.printError(lottoException);
            return true;
        }
        return false;
    }

    private static void settingWinningNumbers() {
        boolean retry = true;
        while (retry) {
            retry = inputWinningNumber();
        }
    }

    private static boolean inputWinningNumber() {
        try {
            String inputWinningNumber = InputView.inputWinningNumber();
            lottoController.inputWinningNumber(inputWinningNumber);
        } catch (LottoException lottoException) {
            OutputView.printError(lottoException);
            return true;
        }
        return false;
    }

    private static void settingBonusNumbers() {
        boolean retry = true;
        while (retry) {
            retry = inputBonusNumber();
        }
    }

    private static boolean inputBonusNumber() {
        try {
            String inputBonusNumber = InputView.inputBonusNumber();
            lottoController.inputBonusNumber(inputBonusNumber);
        } catch (LottoException lottoException) {
            OutputView.printError(lottoException);
            return true;
        }
        return false;
    }

    private static void printWinningResult() {
        System.out.println(lottoController.formattingWinningResult());
    }
}
