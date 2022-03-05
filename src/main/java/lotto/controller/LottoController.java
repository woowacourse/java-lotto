package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketAutoStrategy;
import lotto.domain.LottoTicketStrategy;
import lotto.domain.LottoTickets;
import lotto.domain.LottoTicketsDTO;
import lotto.domain.ManualLottoTicketsGenerator;
import lotto.domain.MoneyManager;
import lotto.domain.Ranks;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void executeManualAndAutoLotto() {
        MoneyManager moneyManager = getMoney();
        int manualLottoCount = getManualLottoCount(moneyManager);
        int autoLottoCount = moneyManager.getPossibleLottoCount();

        LottoTickets lottoTickets = createLottoTickets(manualLottoCount, autoLottoCount);
        displayLottoStatus(manualLottoCount, autoLottoCount, lottoTickets);

        Ranks ranks = Ranks.getRanksFrom(lottoTickets.getRanksWithWinningNumbers(getWinningNumbersAndBonusNumber()));
        OutputView.displayResult(ranks.getStatistics(), moneyManager.calculateYield(ranks.getLottoTotalReward()));
    }

    private void displayLottoStatus(int manualLottoCount, int autoLottoCount, LottoTickets lottoTickets) {
        OutputView.displayLottoCount(manualLottoCount, autoLottoCount);
        OutputView.displayLottoTickets(new LottoTicketsDTO(lottoTickets));
    }

    private MoneyManager getMoney() {
        try {
            return new MoneyManager(InputView.requestMoney());
        } catch (RuntimeException exception) {
            System.out.println("[ERROR] " + exception.getMessage() + "\n");
            return getMoney();
        }
    }

    private int getManualLottoCount(MoneyManager moneyManager) {
        try {
            int manualLottoCount = InputView.requestManualLottoCount();
            moneyManager.decreaseMoney(manualLottoCount);
            return manualLottoCount;
        } catch (RuntimeException exception) {
            System.out.println("[ERROR] " + exception.getMessage() + "\n");
            return getManualLottoCount(moneyManager);
        }
    }

    private LottoTickets createLottoTickets(int manualLottoCount, int autoLottoCount) {
        return createManualLottoTickets(manualLottoCount)
                .combine(createAutoLottoTickets(autoLottoCount, new LottoTicketAutoStrategy()));
    }

    private LottoTickets createManualLottoTickets(int manualLottoCount) {
        try {
            return new LottoTickets(
                    ManualLottoTicketsGenerator.createLottoNumbers(
                            InputView.requestManualLottoNumbers(manualLottoCount)
                    ));
        } catch (RuntimeException exception) {
            System.out.println("[ERROR] " + exception.getMessage() + "\n");
            return createManualLottoTickets(manualLottoCount);
        }
    }

    private LottoTickets createAutoLottoTickets(int autoLottoCount, LottoTicketStrategy lottoTicketGenerator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < autoLottoCount; i++) {
            lottoTickets.add(lottoTicketGenerator.generate());
        }
        return new LottoTickets(lottoTickets);
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

    public void executeAutoLotto() {
        MoneyManager moneyManager = getMoney();
        LottoTickets lottoTickets =
                createAutoLottoTickets(moneyManager.getPossibleLottoCount(), new LottoTicketAutoStrategy());

        OutputView.displaySingleLottoCount(moneyManager.getPossibleLottoCount());
        OutputView.displayLottoTickets(new LottoTicketsDTO(lottoTickets));

        Ranks ranks = Ranks.getRanksFrom(lottoTickets.getRanksWithWinningNumbers(getWinningNumbersAndBonusNumber()));
        OutputView.displayResult(ranks.getStatistics(), moneyManager.calculateYield(ranks.getLottoTotalReward()));
    }
}
