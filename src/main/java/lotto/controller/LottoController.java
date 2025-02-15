package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Profit;
import lotto.domain.Rank;
import lotto.domain.WinnerLotto;
import lotto.dto.LottoGroupDto;
import lotto.dto.ProfitDto;
import lotto.utils.NumberUtils;
import lotto.utils.RecoveryUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        Money money = getMoney();
        LottoGroup lottoGroup = createLottoGroup(money);
        WinnerLotto winnerLotto = getWinnerLotto();

        Profit profit = calculateProfit(winnerLotto, lottoGroup);
        String profitRate = profit.calculateAverageProfitRate(money);

        OutputView.printResult(ProfitDto.from(profit), profitRate);
    }

    private Money getMoney() {
        return RecoveryUtils.executeWithRetry(() -> NumberUtils.parseInt(InputView.readMoney()), Money::new);
    }

    private LottoGroup createLottoGroup(Money money) {
        LottoGroup lottoGroup = LottoGroup.create();
        lottoGroup.generate(money);
        OutputView.printLottoGroup(LottoGroupDto.from(lottoGroup));

        return lottoGroup;
    }

    private Profit calculateProfit(WinnerLotto winnerLotto, LottoGroup lottoGroup) {
        Profit profit = new Profit();

        for (Lotto lotto : lottoGroup.getItem()) {
            long matchCount = winnerLotto.getMatchCount(lotto);
            boolean hasBonus = winnerLotto.hasBonus(lotto);
            Rank rank = Rank.find((int) matchCount, hasBonus);
            profit.incrementCount(rank);
        }

        return profit;
    }

    private WinnerLotto getWinnerLotto() {
        return RecoveryUtils.executeWithRetry(InputView::readWinnerNumbers, this::readWinnerNumber);
    }

    private WinnerLotto readWinnerNumber(String input) {
        Lotto winnerNumbers = Lotto.from(input);

        return readBonusNumber(winnerNumbers);
    }

    private WinnerLotto readBonusNumber(Lotto winnerNumbers) {
        LottoNumber bonusNumber = RecoveryUtils.executeWithRetry(
                () -> NumberUtils.parseInt(InputView.readBonusNumber()), LottoNumber::from);

        try {
            WinnerLotto.validateBonusNumbers(winnerNumbers, bonusNumber);
            return new WinnerLotto(winnerNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return readBonusNumber(winnerNumbers);
        }
    }
}
