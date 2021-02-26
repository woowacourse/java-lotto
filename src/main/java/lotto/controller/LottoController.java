package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import lotto.domain.Count;
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
        try {
            Money money = inputMoney();
            LottoTickets lottoTickets = buyTickets(money);
            WinningLotto winningLotto = inputWinningLotto();
            showResult(lottoTickets, winningLotto, money);
        } catch (LottoCustomException exception) {
            OutputView.printErrorMessage(exception);
        }
    }

    private Money inputMoney() {
        OutputView.printMoneyMessage();
        return new Money(inputView.inputValue());
    }

    private LottoTickets buyTickets(Money money) {
        OutputView.printFixedTicketMessage();
        Count fixedTickets = new Count(money.buyWithinLimit(inputView.inputValue()));
        LottoTickets lottoTickets = new LottoTickets();
        Supplier<List<Integer>> randomNumbersGenerator = RandomUtils::generateNumbers;

        buyManualTickets(lottoTickets, fixedTickets);
        lottoTickets
            .addTickets(inputTickets(fixedTickets.remains(money.countTickets()), randomNumbersGenerator));
        OutputView.printAllTickets(fixedTickets, money, lottoTickets);
        return lottoTickets;
    }

    private WinningLotto inputWinningLotto() {
        OutputView.printWinningNumbers();
        LottoTicket lottoTicket = new LottoTicket(inputView.inputNumbers());
        OutputView.printBonusNumber();
        LottoNumber bonus = new LottoNumber(inputView.inputValue());
        return new WinningLotto(lottoTicket, bonus);
    }

    private void showResult(LottoTickets lottoTickets, WinningLotto winningLotto, Money money) {
        LottoResult lottoResult = new LottoResult();
        lottoResult.checkWinnings(lottoTickets, winningLotto);

        OutputView.printWinningResultTitle();
        OutputView.printProfit(money.calculateProfit(lottoResult.calculateTotalReward()),
            lottoResult.getResults());
    }

    private void buyManualTickets(LottoTickets lottoTickets, Count count) {
        if (count.exists()) {
            OutputView.printInputFixedTicketMessage();
            Supplier<List<Integer>> manualNumbersGenerator = inputView::inputNumbers;

            lottoTickets.addTickets(inputTickets(count, manualNumbersGenerator));
        }
    }

    private List<LottoTicket> inputTickets(Count count, Supplier<List<Integer>> numbersGenerator) {
        List<LottoTicket> tickets = new ArrayList<>();
        IntStream.rangeClosed(1, count.counts())
            .forEach(index -> tickets.add(new LottoTicket(numbersGenerator.get())));
        return tickets;
    }
}
