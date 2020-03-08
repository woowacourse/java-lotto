package lotto.Controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private Money money;
    private IssuedLottoTicket lotto;
    private LottoResult lottoResult = new LottoResult();

    public LottoController() {
        LottoFactory.getInstance();
    }

    public void run() {

        OutputView.printPurchaseCount(buyLotto());
        OutputView.printLottoNumbers(this.lotto.getIssuedLottoTicket());

        drawLotto();

        OutputView.printLottoResults(this.lottoResult.getLottoResult());
        OutputView.printRewardRate(this.lottoResult.calculateRewardRate(this.money.getMoney()));
    }

    private void drawLotto() {
        WinningLotto winningLotto = new WinningLotto(LottoFactory.createManualLottoNumbers(InputView.inputWinningLottoNumbers()), InputView.inputBonus());
        this.lottoResult.analyzeRank(this.lotto.getIssuedLottoTicket(), winningLotto);
    }

    private LottoTicketCount buyLotto() {
        this.money = new Money(InputView.inputMoney());
        LottoTicketCount ticketCount = new LottoTicketCount(this.money.getMoney(), InputView.inputManualTicketNumber());
        this.lotto = new IssuedLottoTicket(ticketCount.getAutoCount(), ticketCount.getManualCount());       //수동 구매 번호도 입력
        return ticketCount;
    }
}
