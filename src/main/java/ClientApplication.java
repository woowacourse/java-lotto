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

public class ClientApplication {

    private final OutputView outputView;
    private final LottoController lottoController;
    private final InputView inputView;

    public ClientApplication() {
        outputView = new OutputView(new LottoBuyResultFormatter(), new WinningCalculateFormatter());
        inputView = new InputView(outputView);
        lottoController = new LottoController(new LottoService(
        ));
    }

    public static void main(String[] args) {
        new ClientApplication().startLotto();
    }

    private void startLotto() {
        LottoDispenser lottoDispenser = buyLotto();
        WinningNumber winningNumber = generateWinningNumber();
        BonusNumber bonusNumber = generateBonusNumbers();
        printWinningResult(lottoDispenser, winningNumber, bonusNumber);
    }

    private LottoDispenser buyLotto() {
        LottoDispenser lottoDispenser = buyLottoInput();
        outputView.printBuyLotto(lottoDispenser.getLottos());
        return lottoDispenser;
    }

    private LottoDispenser buyLottoInput() {
        try {
            String inputBuyLottoMoney = inputView.inputBuyLottoMoney();
            return lottoController.inputBuyLottoMoney(inputBuyLottoMoney);
        } catch (LottoException lottoException) {
            outputView.printError(lottoException);
            return buyLottoInput();
        }
    }

    private WinningNumber generateWinningNumber() {
        return inputWinningNumber();
    }

    private WinningNumber inputWinningNumber() {
        try {
            String inputWinningNumber = inputView.inputWinningNumber();
            return lottoController.inputWinningNumber(inputWinningNumber);
        } catch (LottoException lottoException) {
            outputView.printError(lottoException);
            return inputWinningNumber();
        }
    }

    private BonusNumber generateBonusNumbers() {
        return inputBonusNumber();
    }

    private BonusNumber inputBonusNumber() {
        try {
            String inputBonusNumber = inputView.inputBonusNumber();
            return lottoController.inputBonusNumber(inputBonusNumber);
        } catch (LottoException lottoException) {
            outputView.printError(lottoException);
            return inputBonusNumber();
        }
    }

    private void printWinningResult(LottoDispenser lottoDispenser, WinningNumber winningNumber,
                                    BonusNumber bonusNumber) {
        outputView.printWinningResult(
                lottoController.formattingWinningResult(lottoDispenser, winningNumber, bonusNumber));
    }
}
