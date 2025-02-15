package lotto.controller;

import java.util.List;

import lotto.domain.LottoNumberGenerator;
import lotto.domain.LottoStatistics;
import lotto.domain.Money;
import lotto.domain.LottoShop;
import lotto.dto.WinningInform;
import lotto.domain.Lotto;
import lotto.domain.Wallet;
import lotto.domain.Profit;
import lotto.view.OutputView;

public class Controller {
    private final InputController inputController;
    private final OutputView outputView;

    public Controller(InputController inputController, OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        Money money = inputController.getMoney("구입금액을 입력해 주세요.");
        outputView.print(money.getAmount() + "개를 구매했습니다.\n");

        LottoShop lottoShop = new LottoShop(new LottoNumberGenerator());
        List<Lotto> lottos = lottoShop.buyLottos(money);
        Wallet wallet = new Wallet(lottos);
        outputView.print(wallet.toString());

        WinningInform winningInform = inputController.getWinningInformation();

        LottoStatistics lottoStatistics = LottoStatistics.from(wallet, winningInform);
        Profit profit = lottoStatistics.calculateProfit(money);
        outputView.printResult(lottoStatistics, profit);
    }

}
