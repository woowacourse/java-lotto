package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoManager;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStats;
import lotto.domain.LottoSupplier;
import lotto.domain.Money;
import lotto.domain.RatingInfo;
import lotto.domain.Ticket;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoManager lottoManager = new LottoManager(new LottoSupplier());
        Ticket ticket = buyLotto();

        OutputView.printBuyLotto(ticket.getCount());
        OutputView.printLottoResults(lottoManager.buyLotto(ticket));

        WinningLotto winningLotto = buyWinningLotto();
        RatingInfo ratingInfo = lottoManager.scratchLotto(winningLotto);
        OutputView.printWinningStats(new LottoStats(ratingInfo), ticket.getPrice());
    }

    public static Ticket buyLotto() {
        try {
            return tryBuyLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyLotto();
        }
    }

    public static Ticket tryBuyLotto() {
        int money = InputView.getInputMoney();
        return new Ticket(new Money(money));
    }

    public static WinningLotto buyWinningLotto() {
        try {
            return tryBuyWinningLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyWinningLotto();
        }
    }

    public static WinningLotto tryBuyWinningLotto() {
        Lotto lotto = new Lotto(InputView.getWinningNumbers());
        LottoNumber bonusNumber = new LottoNumber(InputView.getBonusBall());
        return new WinningLotto(lotto, bonusNumber);
    }
}
