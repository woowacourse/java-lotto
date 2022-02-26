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
        int money = inputView.getMoney();
        int totalCount = lottoController.createTotalCount(money);
        outputView.printTotalCount(totalCount);

        LottoTicketsDto lottoTicketsDto = lottoController.createLottoTickets(money);
        outputView.printLottoTicketsInfo(lottoTicketsDto);

        WinningNumberDto winningNumberDto = lottoController.createWinningNumber(
                inputView.getNormalNumbers(), inputView.getBonusNumber());

        outputView.printLottoResultMessage();
        LottoResultDto lottoResultDto = lottoController.createYield(money, winningNumberDto, lottoTicketsDto);
        outputView.printYield(lottoResultDto);
    }
}
