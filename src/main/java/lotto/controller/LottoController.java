package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.Money;
import lotto.domain.Profit;
import lotto.domain.Rank;
import lotto.domain.WinnerLotto;
import lotto.dto.LottoGroupDto;
import lotto.dto.ProfitDto;
import lotto.utils.RecoveryUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    LottoGroup lottoGroup = new LottoGroup();

    public void run() {
        Money money = getMoney();
        generateLotto(money);
        WinnerLotto winnerLotto = getWinnerLotto();

        Profit profit = calculateProfit(winnerLotto);
        String profitRate = profit.calculateAverageProfitRate(money);

        OutputView.printResult(ProfitDto.from(profit), profitRate);
    }

    private Money getMoney() {
        return RecoveryUtils.executeWithRetry(InputView::readMoney, Money::new);
    }

    private void generateLotto(Money money) {
        lottoGroup.generate(money);
        OutputView.printLottoGroup(LottoGroupDto.from(lottoGroup));
    }

    private Profit calculateProfit(WinnerLotto winnerLotto) {
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
        LottoNumbers winnerNumbers = LottoNumbers.from(input);

        return readBonusNumber(winnerNumbers);
    }

    private WinnerLotto readBonusNumber(LottoNumbers winnerNumbers) {
        LottoNumber bonusNumber = RecoveryUtils.executeWithRetry(InputView::readBonusNumber, LottoNumber::create);

        try {
            WinnerLotto.validateBonusNumbers(winnerNumbers, bonusNumber);
            return new WinnerLotto(winnerNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return readBonusNumber(winnerNumbers);
        }
    }
}
