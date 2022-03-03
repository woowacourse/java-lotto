import controller.LottoController;
import dto.LottoCountDto;
import dto.LottosDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();
        LottoController lottoController = new LottoController();

        LottoCountDto lottoCountDto = lottoController.selectLottoCount(inputView.inputPurchaseAmount(), inputView.inputManualLottoCount());

        lottoController.purchaseManualLotto(inputView.inputManualLottoNumber(lottoCountDto.getManualLottoCount()));
        LottosDto lottosDto = lottoController.purchase();

        outputView.printPurchasedLotto(lottoCountDto, lottosDto);

        lottoController.determineWinningNumber(inputView.inputWinningNumber(), inputView.inputBonusBall());

        outputView.printResult(lottoController.makeResult());
    }
}
