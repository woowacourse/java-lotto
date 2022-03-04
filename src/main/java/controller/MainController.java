package controller;

import domain.lotto.Lotto;
import domain.lotto.LottoFactory;
import domain.lotto.LottoMoney;
import domain.lotto.LottoTicketCount;
import domain.lotto.WinningLotto;
import domain.result.Result;
import java.util.List;
import utils.Util;
import view.InputView;
import view.ResultView;

public class MainController {
    private MainController() {
    }

    public static MainController create() {
        return new MainController();
    }

    public void run() {
        final LottoMoney lottoMoney = makeLottoMoney();
        final LottoTicketCount count = makeLottoTicketCount(lottoMoney);
        final List<Lotto> lottoTickets = makeLottos(count);
        ResultView.printLottoTickets(count, lottoTickets);

        final WinningLotto winningLotto = makeWinNums();

        final Result result = Result.of(lottoTickets, winningLotto);
        end(result, lottoMoney);
    }

    private LottoMoney makeLottoMoney() {
        return LottoMoney.from(
                InputView.inputMoney());
    }

    private LottoTicketCount makeLottoTicketCount(final LottoMoney money) {
        return LottoTicketCount.of(money.toLottoCount(),
                InputView.inputManualTicketCount());
    }

    private List<Lotto> makeLottos(final LottoTicketCount count) {
        return LottoFactory.createLottos(count,
                InputView.inputManualNums(count.ofManual()));
    }

    private WinningLotto makeWinNums() {
        return LottoFactory.createWinNums(InputView.inputWinLottoNums(), InputView.inputBonusNumber());
    }

    private void end(final Result result, final LottoMoney lottoMoney) {
        ResultView.printLottosResult(result);
        ResultView.printProfit(Util.getProfit((float) result.getPrize(), (float) lottoMoney.get()));
    }
}