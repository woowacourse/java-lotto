package lotto;

import lotto.domain.lotto.LottoTicketFactory;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.money.LottoMoney;
import lotto.domain.result.LottoResult;
import lotto.parser.GameParser;
import lotto.view.input.InputView;
import lotto.view.output.OutputView;

public class Application {

    private static GameParser gameParser = new GameParser();

    public static void main(String[] args) {
        LottoMoney lottoMoney = new LottoMoney(gameParser.parseInputToInt(InputView.inputLottoMoney()));
        LottoTickets lottoTickets = LottoTicketFactory.publishLottoTickets(lottoMoney);
        OutputView.printLottoTickets(lottoTickets);
        WinningLotto winningLotto = LottoTicketFactory.publishWinningLotto(InputView.inputWinningLotto(), InputView.inputBonusNumber());
        LottoResult lottoResult = lottoTickets.getLottoResults(winningLotto);
        OutputView.printLottoResult(lottoResult);
        OutputView.printProfit(lottoResult, lottoMoney);
    }
}
