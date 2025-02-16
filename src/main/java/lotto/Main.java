package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.Money;
import lotto.domain.Profit;
import lotto.domain.WinnerLotto;
import lotto.service.LottoService;
import lotto.service.RandomLottoServiceImpl;
import lotto.utils.RecoveryUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {

    private static final LottoService lottoService = new LottoService(new RandomLottoServiceImpl());

    public static void main(String[] args) {
        Money money = RecoveryUtils.executeWithRetry(() -> lottoService.getMoney(InputView.readMoney()));
        LottoGroup lottoGroup = lottoService.generateLottoGroupByMoney(money);
        printLottoGroup(money, lottoGroup);

        Lotto winnerNumber = getWinnerNumberWithRetry();
        WinnerLotto winnerLotto = getWinnerLottoWithRetry(winnerNumber);

        Profit profit = lottoService.calculateProfit(winnerLotto, lottoGroup);
        printStatics(profit, money);
    }

    private static void printLottoGroup(Money money, LottoGroup lottoGroup) {
        int lottoTicketCount = money.getLottoTicketCount();
        String lottoGroupMessage = lottoService.getLottoGroupMessage(lottoGroup);

        OutputView.printLottoGroup(lottoTicketCount, lottoGroupMessage);
    }

    private static Lotto getWinnerNumberWithRetry() {
        return RecoveryUtils.executeWithRetry(
                () -> lottoService.getWinnerNumber(InputView.readWinnerNumbers()));
    }

    private static WinnerLotto getWinnerLottoWithRetry(Lotto winnerNumber) {
        return RecoveryUtils.executeWithRetry(
                () -> lottoService.getWinnerLotto(winnerNumber, InputView.readBonusNumber()));
    }

    private static void printStatics(Profit profit, Money money) {
        List<Integer> statusValues = lottoService.getStatusValues(profit);
        String profitRate = lottoService.getProfitRate(profit, money);

        OutputView.printStatics(statusValues, profitRate);
    }
}
