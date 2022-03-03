package lotto;

import lotto.controller.LottoController;
import lotto.controller.OrderController;
import lotto.dto.LottoResultDto;
import lotto.dto.LottoTicketsDto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OrderController orderController = new OrderController();
        orderController.takeOrder(InputView.requestMoney(), InputView.requestManualLotto());

        LottoController lottoController = new LottoController(orderController.getLottoOrder());
        LottoTicketsDto lottoTicketsDto = lottoController.publishLottoTickets();
        OutputView.displayLottoTickets(lottoTicketsDto);

        LottoResultDto lottoResultDto = lottoController.matchLottoTickets(InputView.requestWinningNumbers(),
                InputView.requestBonusNumber());
        OutputView.displayLottoResult(lottoResultDto);
    }
}
