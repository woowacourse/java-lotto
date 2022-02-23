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
        List<LottoTicket> lottoTickets = purchaseLottoTickets();
        createResult(lottoTickets);
    }

    private List<LottoTicket> purchaseLottoTickets() {
        Money money = Money.from(InputView.getMoney());
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(money, lottoNumberStrategy);

        OutputView.printPurchasedLottoTicketNumber(lottoTickets.size());
        OutputView.printPurchasedLottoTickets(lottoTickets);

        return lottoTickets;
    }

    private void createResult(List<LottoTicket> lottoTickets) {
        LottoTicketNumbers winningNumbers = new LottoTicketNumbers(InputView.getWinningNumbers().stream()
                .map(LottoNumber::create)
                .collect(Collectors.toList()));
        LottoNumber bonusNumber = LottoNumber.createBonus(InputView.getBonusNumber(), winningNumbers);

        WinningStat winningStat = lottoMachine.createWinningStat(lottoTickets, winningNumbers, bonusNumber);
        OutputView.printWinningStat(winningStat);
    }
}
