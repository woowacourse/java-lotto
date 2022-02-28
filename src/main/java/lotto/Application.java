package lotto;

import java.util.List;
import lotto.controller.LottoController;
import lotto.controller.dto.LottoTicketsDto;
import lotto.controller.dto.PurchaseInfoDto;
import lotto.controller.dto.WinningNumberDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        int money = createMoney(inputView, outputView);
        int manualCount = createManualCount(inputView, outputView);
        LottoTicketsDto manualNumbers = LottoTicketsDto.from(inputView.getManualNumbers(manualCount));

        PurchaseInfoDto purchaseInfoDto = PurchaseInfoDto.valueOf(money, manualCount, manualNumbers);
    }

    private static int createMoney(InputView inputView, OutputView outputView) {
        try {
            return inputView.getMoney();
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return createMoney(inputView, outputView);
        }
    }

    private static int createManualCount(InputView inputView, OutputView outputView) {
        try {
            return inputView.getManualCount();
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return createManualCount(inputView, outputView);
        }
    }

    private static WinningNumberDto creatWinningNumber(LottoController controller, InputView input, OutputView output) {
        try {
            List<Integer> normalNumbers = input.getNormalNumbers();
            int bonusNumber = input.getBonusNumber();

            return controller.createWinningNumber(normalNumbers, bonusNumber);
        } catch (RuntimeException e) {
            output.printErrorMessage(e.getMessage());
            return creatWinningNumber(controller, input, output);
        }
    }
}
