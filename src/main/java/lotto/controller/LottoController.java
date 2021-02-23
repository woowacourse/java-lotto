package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.exception.LottoCustomException;
import lotto.utils.RandomUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;

    public LottoController() {
        inputView = new InputView();
    }

    public void run() {
        Money money = inputMoney();
        LottoTickets lottoTickets = buyTickets(money);

        WinningLotto winningLotto = inputWinningLotto();

        showResult(lottoTickets, winningLotto, money);
    }

    private Money inputMoney() {
        try {
            OutputView.printMoneyMessage();
            return new Money(inputView.inputValue());
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputMoney();
        }
    }

    private LottoTickets buyTickets(Money money) {
        LottoTickets lottoTickets = new LottoTickets();
        int fixedTickets = inputManualTicketsCount(money);
        buyManualTickets(lottoTickets, fixedTickets);
        buyRandomTickets(lottoTickets, money.countTickets() - fixedTickets);
        OutputView.printAllTickets(fixedTickets, money, lottoTickets);
        return lottoTickets;
    }

    private void buyManualTickets(LottoTickets lottoTickets, int count) {
        if (count > 0) {
            OutputView.printInputFixedTicketMessage();
            lottoTickets.addTickets(inputManualTickets(count));
        }
    }

    private void buyRandomTickets(LottoTickets lottoTickets, int count) {
        if (count > 0) {
            lottoTickets.addTickets(inputRandomTickets(count));
        }
    }

    private List<LottoTicket> inputRandomTickets(int count) {
        List<LottoTicket> tickets = new ArrayList<>();
        IntStream.rangeClosed(1, count)
            .forEach(index -> {
                tickets.add(new LottoTicket(RandomUtils.generateNumbers()));
            });
        return tickets;
    }

    private int inputManualTicketsCount(Money money) {
        try {
            OutputView.printFixedTicketMessage();
            int manualTickets = inputView.inputValue();
            money.checkLimit(manualTickets);
            return manualTickets;
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputManualTicketsCount(money);
        }
    }

    private List<LottoTicket> inputManualTickets(int count) {
        List<LottoTicket> tickets = new ArrayList<>();
        IntStream.rangeClosed(1, count)
            .forEach(index -> {
                tickets.add(inputManualTicket());
            });
        return tickets;
    }

    private LottoTicket inputManualTicket() {
        try {
            return new LottoTicket(inputView.inputNumbers());
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputManualTicket();
        }
    }

    private WinningLotto inputWinningLotto() {
        OutputView.printWinningNumbers();
        LottoTicket lottoTicket = inputManualTicket();
        LottoNumber bonus = inputBonus(lottoTicket);
        return new WinningLotto(lottoTicket, bonus);
    }

    private LottoNumber inputBonus(LottoTicket lottoTicket) {
        try {
            OutputView.printBonusNumber();
            LottoNumber bonusNumber = new LottoNumber(inputView.inputValue());
            bonusNumber.validateDuplicate(lottoTicket);
            return bonusNumber;
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
            return inputBonus(lottoTicket);
        }
    }

    private void showResult(LottoTickets lottoTickets, WinningLotto winningLotto, Money money) {
        LottoResult lottoResult = new LottoResult();
        lottoResult.checkWinnings(lottoTickets, winningLotto);

        OutputView.printWinningResultTitle();
        OutputView.printProfit(money.calculateProfit(lottoResult.calculateTotalReward()),
            lottoResult.getResults());
    }
}
