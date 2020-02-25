package lotto;

import lotto.domain.count.Count;
import lotto.domain.lotto.LottoTicketFactory;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.money.LottoMoney;
import lotto.domain.result.LottoResult;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoMoney lottoMoney = new LottoMoney(InputView.inputLottoMoney());
        Count count = new Count(lottoMoney.getLottoPurchaseCounts(), InputView.inputManualCounts());

        LottoTickets lottoTickets = LottoTicketFactory.publishLottoTickets(count);
        OutputView.printLottoTickets(lottoTickets);
        WinningLotto winningLotto = LottoTicketFactory.publishWinningLotto(InputView.inputWinningLotto());
        LottoResult lottoResult = lottoTickets.getLottoResults(winningLotto);
        OutputView.printLottoResult(lottoResult);
        OutputView.printProfit(lottoResult, lottoMoney);
    }
}
