package lotto.console;

import lotto.controller.LottoController;
import lotto.controller.dto.LottoResultDto;
import lotto.controller.dto.LottoTicketsDto;
import lotto.controller.dto.WinningNumberDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoRunner {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoController lottoController;

    public LottoRunner(InputView inputView, OutputView outputView, LottoController lottoController) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoController = lottoController;
    }

    public void run() {
        int money = createMoney();
        int totalCount = lottoController.createTotalCount(money);
        outputView.printTotalCount(totalCount);

        LottoTicketsDto lottoTicketsDto = lottoController.createLottoTickets(money);
        outputView.printLottoTicketsInfo(lottoTicketsDto);

        WinningNumberDto winningNumberDto = creatWinningNumber();

        outputView.printLottoResultMessage();
        LottoResultDto lottoResultDto = lottoController.createLottoResult(money, winningNumberDto, lottoTicketsDto);
        outputView.printYield(lottoResultDto);
    }

    private int createMoney() {
        try {
            return inputView.getMoney();
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return createMoney();
        }
    }

    private WinningNumberDto creatWinningNumber() {
        try {
            return lottoController.createWinningNumber(inputView.getNormalNumbers(), inputView.getBonusNumber());
        } catch (RuntimeException e) {
            outputView.printErrorMessage(e.getMessage());
            return creatWinningNumber();
        }
    }
}
