package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMain {

    public static void main(String[] args) {
        LottoFactory.getInstance();
        Money money = new Money(InputView.inputMoney());
        LottoTicketCount ticketCount = new LottoTicketCount(money.getMoney(),InputView.inputManualTicketNumber());
        IssuedLottoTicket lotto = new IssuedLottoTicket(ticketCount.getAutoCount(),ticketCount.getManualCount());       //수동 구매 번호도 여기서 입력

        OutputView.printPurchaseCount(ticketCount.getAutoCount(),ticketCount.getManualCount());
        OutputView.printLottoNumbers(lotto.getIssuedLottoTicket());

        WinningLotto winningLotto = new WinningLotto(LottoFactory.createManualLottoNumbers(InputView.inputWinningLottoNumbers()),InputView.inputBonus());

        LottoResult lottoResult = new LottoResult();
        lottoResult.analyzeRank(lotto.getIssuedLottoTicket(),winningLotto);
        OutputView.printLottoResults(lottoResult.getLottoResult());
        OutputView.printRewardRate(lottoResult.calculateRewardRate(money.getMoney()));
    }
}
