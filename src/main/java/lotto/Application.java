package lotto;

import java.util.List;
import lotto.controller.LottoController;
import lotto.controller.dto.LottoResultDto;
import lotto.controller.dto.PurchaseInfoDto;
import lotto.controller.dto.SalesInfoDto;
import lotto.controller.dto.WinningNumberDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        SalesInfoDto salesInfoDto = createSalesInfo(lottoController, inputView, outputView);
        outputView.printSalesInfo(salesInfoDto);

        WinningNumberDto winningNumberDto = creatWinningNumber(lottoController, inputView, outputView);

        outputView.printLottoResultMessage();
        LottoResultDto lottoResultDto = lottoController.createLottoResult(salesInfoDto, winningNumberDto);
        outputView.printYield(lottoResultDto);
    }

    private static SalesInfoDto createSalesInfo(LottoController lottoController, InputView inputView,
                                                OutputView outputView) {
        try {
            int money = inputView.getMoney();
            int manualCount = inputView.getManualCount();
            List<List<Integer>> manualNumbers = inputView.getManualNumbers(manualCount);

            return lottoController.purchase(PurchaseInfoDto.valueOf(money, manualCount, manualNumbers));
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return createSalesInfo(lottoController, inputView, outputView);
        }
    }

    private static WinningNumberDto creatWinningNumber(LottoController controller, InputView inputView,
                                                       OutputView outputView) {
        try {
            List<Integer> normalNumbers = inputView.getNormalNumbers();
            int bonusNumber = inputView.getBonusNumber();

            return controller.createWinningNumber(normalNumbers, bonusNumber);
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return creatWinningNumber(controller, inputView, outputView);
        }
    }
}
