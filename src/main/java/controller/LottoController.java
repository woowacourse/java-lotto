package controller;

import domain.*;
import domain.strategy.LottoNumberStrategy;
import domain.strategy.RandomLottoNumberStrategy;
import view.InputView;
import view.OutputView;

import java.util.List;

import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class LottoController {

    private final LottoMachine lottoMachine = new LottoMachine();

    public void run() {
        List<LottoTicket> lottoTickets = purchaseLottoTickets();
        createResult(lottoTickets);
    }

    private List<LottoTicket> purchaseLottoTickets() {
        Money money = Money.from(InputView.getMoney());
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(money, new RandomLottoNumberStrategy());

        OutputView.printPurchasedLottoTicketNumber(lottoTickets.size());
        OutputView.printPurchasedLottoTickets(lottoTickets);

        return lottoTickets;
    }

    private void createResult(List<LottoTicket> lottoTickets) {
        LottoTicketNumbers winningNumbers = LottoTicketNumbers.convertToLottoNumber(InputView.getWinningNumbers());
        LottoNumber bonusNumber = LottoNumber.createBonus(InputView.getBonusNumber(), winningNumbers);

        WinningStat winningStat = lottoMachine.createWinningStat(lottoTickets, winningNumbers, bonusNumber);
        OutputView.printWinningStat(winningStat, winningStat.calculateProfit(LOTTO_TICKET_PRICE));
    }
}
