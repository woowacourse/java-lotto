package lotto;

import java.util.List;
import lotto.controller.LottoController;
import lotto.controller.dto.LottoResultDto;
import lotto.controller.dto.LottoTicketsDto;
import lotto.controller.dto.WinningNumberDto;
import lotto.controller.dto.MoneyDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        int money = createMoney(inputView, outputView);
        MoneyDto moneyDto = lottoController.createMoney(money);
        outputView.printTotalCount(moneyDto);

        LottoTicketsDto lottoTicketsDto = lottoController.createLottoTickets(money);
        outputView.printLottoTicketsInfo(lottoTicketsDto);

        WinningNumberDto winningNumberDto = creatWinningNumber(lottoController, inputView, outputView);

        outputView.printLottoResultMessage();
        LottoResultDto lottoResultDto = lottoController.createLottoResult(money, winningNumberDto,
                lottoTicketsDto);
        outputView.printYield(lottoResultDto);
    }

    private static int createMoney(InputView inputView, OutputView outputView) {
        try {
            return inputView.getMoney();
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return createMoney(inputView, outputView);
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
