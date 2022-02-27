package lotto;

import lotto.controller.LottoController;
import lotto.controller.dto.LottoResultDto;
import lotto.controller.dto.LottoTicketsDto;
import lotto.controller.dto.WinningNumberDto;
import lotto.controller.dto.money.MoneyRequestDto;
import lotto.controller.dto.money.MoneyResponseDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        MoneyRequestDto moneyRequestDto = createMoney(inputView, outputView);
        MoneyResponseDto moneyResponseDto = lottoController.createMoney(moneyRequestDto);
        outputView.printTotalCount(moneyResponseDto);

        LottoTicketsDto lottoTicketsDto = lottoController.createLottoTickets(moneyRequestDto.getPrice());
        outputView.printLottoTicketsInfo(lottoTicketsDto);

        WinningNumberDto winningNumberDto = creatWinningNumber(lottoController, inputView, outputView);

        outputView.printLottoResultMessage();
        LottoResultDto lottoResultDto = lottoController.createLottoResult(moneyRequestDto.getPrice(), winningNumberDto, lottoTicketsDto);
        outputView.printYield(lottoResultDto);
    }

    private static MoneyRequestDto createMoney(InputView inputView, OutputView outputView) {
        try {
            return inputView.getMoney();
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return createMoney(inputView, outputView);
        }
    }

    private static WinningNumberDto creatWinningNumber(LottoController lottoController, InputView inputView,
                                                OutputView outputView) {
        try {
            return lottoController.createWinningNumber(inputView.getNormalNumbers(), inputView.getBonusNumber());
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return creatWinningNumber(lottoController, inputView, outputView);
        }
    }
}
