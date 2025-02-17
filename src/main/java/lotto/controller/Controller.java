package lotto.controller;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.LottoNumber.*;

import java.util.List;

import lotto.domain.RandomNumbersGenerator;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoShop;
import lotto.domain.WinningInform;
import lotto.domain.Lotto;
import lotto.domain.Wallet;
import lotto.domain.Profit;
import lotto.dto.WalletDto;
import lotto.view.OutputView;

public class Controller {
    private final InputController inputController;
    private final OutputView outputView;

    public Controller(InputController inputController, OutputView outputView) {
        this.inputController = inputController;
        this.outputView = outputView;
    }

    public void run() {
        int money = inputController.getMoney();
        LottoShop lottoShop = new LottoShop(
                new RandomNumbersGenerator(LOTTO_RANGE_MINIMUM, LOTTO_RANGE_MAXIMUM, LOTTO_SIZE));
        int purchasedLottoCount = lottoShop.calculateLottoCount(money);
        outputView.printLottoCount(purchasedLottoCount);

        List<Lotto> lottos = lottoShop.buyLottos(money);
        Wallet wallet = new Wallet(lottos);
        outputView.printWallet(WalletDto.from(wallet));

        WinningInform winningInform = inputController.getWinningInform();

        LottoStatistics lottoStatistics = LottoStatistics.from(wallet, winningInform);
        Profit profit = Profit.from(lottoStatistics.getTotalPrize(), money);
        outputView.printResult(lottoStatistics, profit);
    }

}
