package controller;

import domain.lotto.Lotto;
import domain.lotto.LottoFactory;
import domain.lotto.LottoMoney;
import domain.lotto.LottoNumber;
import domain.lotto.LottoTicketCount;
import domain.lotto.WinNumbers;
import domain.result.Result;
import java.util.List;
import utils.NumsGenerator;
import utils.Util;
import view.InputView;
import view.OutputView;

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
        OutputView.printLottoTickets(count, lottoTickets);

        final WinNumbers winNumbers = makeWinNums();

        final Result result = Result.of(lottoTickets, winNumbers);
        end(result, lottoMoney);
    }

    private LottoMoney makeLottoMoney() {
        return LottoMoney.from(
                InputView.inputMoney());
    }

    private LottoTicketCount makeLottoTicketCount(LottoMoney money) {
        return LottoTicketCount.of(money.toLottoCount(),
                InputView.inputManualTicketCount());
    }

    private List<Lotto> makeLottos(final LottoTicketCount count) {
        return LottoFactory.createLottos(count,
                InputView.inputManualNums(count.ofManual()));
    }

    private WinNumbers makeWinNums() {
        LottoNumber bonus = LottoNumber.getInstance(InputView.inputBonusNumber());
        return LottoFactory.createWinNums(InputView.inputWinLottoNums(), InputView.inputBonusNumber());
    }

    private void end(final Result result, final LottoMoney lottoMoney) {
        OutputView.printLottosResult(result);
        OutputView.printProfit(Util.getProfit((float) result.getPrize(), (float) lottoMoney.get()));
    }
}