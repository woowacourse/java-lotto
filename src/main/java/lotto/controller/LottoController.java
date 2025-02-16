package lotto.controller;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGenerator;
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

    private final LottoGenerator lottoGenerator;
    private final LottoGroup lottoGroup;

    public LottoController(LottoGenerator lottoGenerator, LottoGroup lottoGroup) {
        this.lottoGenerator = lottoGenerator;
        this.lottoGroup = lottoGroup;
    }

    public void run() {
        Money money = getMoney();
        processLottoGeneration(money);
        WinnerLotto winnerLotto = getWinnerLotto();

        Profit profit = calculateProfit(winnerLotto);
        String profitRate = profit.calculateAverageProfitRate(money);

        OutputView.printResult(ProfitDto.from(profit), profitRate);
    }

    private Money getMoney() {
        return RecoveryUtils.executeWithRetry(InputView::readMoney, Money::new);
    }

    private void processLottoGeneration(Money money) {
        lottoGroup.processLottoTicketGeneration(money, lottoGenerator);
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
        WinnerLotto.validateInputWinnerNumbers(input);
        List<LottoNumber> winnerNumbers = parseLottoNumbers(input);
        WinnerLotto.validateWinnerNumbers(winnerNumbers);

        return readBonusNumber(winnerNumbers);
    }


    public List<LottoNumber> parseLottoNumbers(String input) {
        return Arrays.stream(input.split(", "))
                .map(LottoNumber::new)
                .toList();
    }

    private WinnerLotto readBonusNumber(List<LottoNumber> winnerNumbers) {
        LottoNumber bonusNumber = RecoveryUtils.executeWithRetry(InputView::readBonusNumber, LottoNumber::new);

        try {
            WinnerLotto.validateBonusNumbers(winnerNumbers, bonusNumber);
            return new WinnerLotto(winnerNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return readBonusNumber(winnerNumbers);
        }
    }
}
