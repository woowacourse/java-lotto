import controller.LottoController;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
import dto.LottoCountDto;
import dto.LottosDto;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = InputView.getInstance();
        OutputView outputView = OutputView.getInstance();
        LottoController lottoController = new LottoController();

        LottoCountDto lottoCountDto = lottoController.selectLottoCount(inputView.inputPurchaseAmount(), inputView.inputManualLottoCount());

        lottoController.purchaseLotto(new ManualLottoGenerator(), inputView.inputManualLottoNumber(lottoCountDto.getManualLottoCount()));
        LottosDto lottosDto = lottoController.purchaseLotto(new AutoLottoGenerator(), null);

        outputView.printPurchasedLotto(lottoCountDto, lottosDto);

        lottoController.determineWinningNumber(inputView.inputWinningNumber(), inputView.inputBonusBall());

        outputView.printResult(lottoController.makeResult());
    }
}
