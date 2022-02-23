package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final LottoMachine lottoMachine = new LottoMachine();
    private final LottoNumberStrategy lottoNumberStrategy = new RandomLottoNumberStrategy();

    public void run() {
        LottoTickets lottoTickets = purchaseLottoTickets();
        createResult(lottoTickets);
    }

    private LottoTickets purchaseLottoTickets() {
        Money money = Money.from(InputView.getMoney());
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(money, lottoNumberStrategy);

        OutputView.printPurchasedLottoTicketNumber(lottoTickets.size());
        OutputView.printPurchasedLottoTickets(lottoTickets);

        return new LottoTickets(lottoTickets);
    }

    private void createResult(LottoTickets lottoTickets) {
        LottoTicketNumbers winningNumbers = new LottoTicketNumbers(InputView.getWinningNumbers().stream()
                .map(LottoNumber::create)
                .collect(Collectors.toList()));
        LottoNumber bonusNumber = LottoNumber.createBonus(InputView.getBonusNumber(), winningNumbers);

        WinningStat winningStat = lottoMachine.createWinningStat(lottoTickets, winningNumbers, bonusNumber);
        OutputView.printWinningStat(winningStat);
    }
}
