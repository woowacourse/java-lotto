package lotto.controller;

import lotto.domain.*;
import lotto.domain.ticketfactory.FixedNumberTicketFactory;
import lotto.exception.LottoCustomException;
import lotto.utils.ValidateUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.*;

public class LottoController {
    private static final int EXIST = 1;
    private static final String SEPARATOR = ",";
    private final InputView inputView;

    public LottoController(final Scanner scanner) {
        inputView = new InputView(scanner);
    }

    public void run() {
        Money money = inputMoney();
        LottoTickets lottoTickets = buyLottoTickets(money);

        LottoTicket winningTicket = makeWinningTicket();
        LottoNumber bonusBall = makeBonusNumber(winningTicket);

        showResult(money, lottoTickets, winningTicket, bonusBall);
        ifChangeExist(money);
    }

    private void ifChangeExist(Money money) {
        if (money.getChange() >= EXIST) {
            OutputView.printGiveChange(money.getChange());
        }
    }

    private Money inputMoney() {
        try {
            OutputView.printMoneyMessage();
            Money money = new Money(ValidateUtils.parseInt(inputView.inputValue()));
            OutputView.printTicketCountMessage(money.getTicketCount());
            return money;
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputMoney();
        }
    }

    private LottoTickets buyLottoTickets(Money money) {
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.makeTicketByCount(money.getTicketCount());
        OutputView.printAllTickets(lottoTickets);
        return lottoTickets;
    }

    private LottoTicket makeWinningTicket() {
        try {
            OutputView.printWinningNumbers();
            return FixedNumberTicketFactory
                    .makeTicket(splitWinningNumbers(inputView.inputValue()));
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeWinningTicket();
        }
    }

    private Set<String> splitWinningNumbers(String winningNumbers) {
        return new HashSet<>(Arrays.asList(winningNumbers.split(SEPARATOR)));
    }

    private LottoNumber makeBonusNumber(LottoTicket winningTicket) {
        try {
            OutputView.printBonusNumber();
            LottoNumber bonusBall = new LottoNumber(ValidateUtils.parseInt(inputView.inputValue()));
            winningTicket.checkDuplicateNumber(bonusBall);
            return bonusBall;
        } catch (LottoCustomException e) {
            OutputView.printErrorMessage(e.getMessage());
            return makeBonusNumber(winningTicket);
        }
    }

    private void showResult(Money money,
                            LottoTickets lottoTickets,
                            LottoTicket winningTicket,
                            LottoNumber bonusBall) {
        List<Integer> hitCounts = lottoTickets.checkHitCount(winningTicket, bonusBall);
        int totalReward = WinningResult.calculateWinnings(hitCounts);
        OutputView.printTotalWinningResult(money.getProfit(totalReward),
                WinningResult.toString(hitCounts));
    }
}
