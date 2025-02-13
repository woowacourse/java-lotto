package lotto.controller;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGroup;
import lotto.domain.LottoNumber;
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

        for (Lotto lotto : lottoGroup.getLottoGroup()) {
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
        List<LottoNumber> winnerNumbers = toLottoNumberList(input);
        WinnerLotto.validateWinnerNumbers(winnerNumbers);

        return readBonusNumber(winnerNumbers);
    }

    private WinnerLotto readBonusNumber(List<LottoNumber> winnerNumbers) {
        LottoNumber bonusNumber = RecoveryUtils.executeWithRetry(InputView::readBonusNumber, LottoNumber::create);

        try {
            WinnerLotto.validateBonusNumbers(winnerNumbers, bonusNumber);
            return new WinnerLotto(winnerNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return readBonusNumber(winnerNumbers);
        }
    }

    private List<LottoNumber> toLottoNumberList(String input) {
        if (input == null || input.isBlank() || input.endsWith(", ")) {
            throw new IllegalArgumentException("잘못된 입력입니다. 이와 같은 형태로 작성해주세요.(ex. 1, 2, 3, 4, 5, 6)");
        }

        return Arrays.stream(input.split(", "))
                .map(LottoNumber::new)
                .toList();
    }


}
