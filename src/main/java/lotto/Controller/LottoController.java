package lotto.Controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private Money money;
    private LottoTicketCount ticketCount;
    private IssuedLottoTicket lotto;
    private WinningLotto winningLotto;
    private LottoResult lottoResult = new LottoResult();

    public LottoController() {
        LottoFactory.getInstance();
    }

    public void run() {
        buyLotto();

        OutputView.printPurchaseCount(ticketCount.getAutoCount(), ticketCount.getManualCount());
        OutputView.printLottoNumbers(lotto.getIssuedLottoTicket());

        drawLotto();

        OutputView.printLottoResults(lottoResult.getLottoResult());
        OutputView.printRewardRate(lottoResult.calculateRewardRate(money.getMoney()));
    }

    private void drawLotto() {
        winningLotto = new WinningLotto(LottoFactory.createManualLottoNumbers(InputView.inputWinningLottoNumbers()), InputView.inputBonus());
        lottoResult.analyzeRank(lotto.getIssuedLottoTicket(), winningLotto);
    }

    private void buyLotto() {
        money = new Money(InputView.inputMoney());
        ticketCount = new LottoTicketCount(money.getMoney(), InputView.inputManualTicketNumber());
        lotto = new IssuedLottoTicket(ticketCount.getAutoCount(), ticketCount.getManualCount());       //수동 구매 번호도 입력
    }
}
