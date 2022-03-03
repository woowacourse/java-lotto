package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumberAutoStrategy;
import lotto.domain.LottoTickets;
import lotto.domain.LottoTicketsDTO;
import lotto.domain.MoneyManager;
import lotto.domain.Ranks;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void buyAndMatch() {
        MoneyManager moneyManager = getMoney();
        LottoTickets lottoTickets = LottoTickets.create(LottoNumberAutoStrategy.generateAutoLottoNumber(moneyManager.getLottoCount()));

        OutputView.displayLottoTickets(new LottoTicketsDTO(lottoTickets));

        WinningNumbers winningNumbers = getWinningNumbersAndBonusNumber();

        Ranks ranks = Ranks.getRanksFrom(lottoTickets.getRanksWithWinningNumbers(winningNumbers));
        OutputView.displayResult(ranks.getStatistics(), moneyManager.calculateYield(ranks.getLottoTotalReward()));
    }

    private MoneyManager getMoney() {
        try {
            return new MoneyManager(InputView.requestMoney());
        } catch (RuntimeException exception) {
            System.out.println("[ERROR] " + exception.getMessage() + "\n");
            return getMoney();
        }
    }

    private WinningNumbers getWinningNumbersAndBonusNumber() {
        try {
            return new WinningNumbers(getWinningNumbers(), getBonusNumber());
        } catch (RuntimeException exception) {
            System.out.println("[ERROR] " + exception.getMessage() + "\n");
            return getWinningNumbersAndBonusNumber();
        }
    }

    private List<LottoNumber> getWinningNumbers() throws RuntimeException {
        return InputView.requestWinningNumbers().stream().map(LottoNumber::valueOf).collect(Collectors.toList());
    }

    private LottoNumber getBonusNumber() throws RuntimeException {
        return LottoNumber.valueOf(InputView.requestBonusNumber());
    }
}
