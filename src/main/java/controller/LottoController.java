package controller;

import domain.*;
import domain.strategy.RandomLottoNumberStrategy;
import view.InputView;
import view.OutputView;

import java.util.List;

import static domain.LottoMachine.LOTTO_TICKET_PRICE;

public class LottoController {

    public void run() {
        LottoMachine lottoMachine = new LottoMachine();
        List<LottoTicket> lottoTickets = purchaseLottoTickets(lottoMachine);
        createResult(lottoMachine, lottoTickets);
    }

    private List<LottoTicket> purchaseLottoTickets(LottoMachine lottoMachine) {
        Money money = new Money(InputView.getMoney());
        PurchaseType purchaseType = new PurchaseType(money, InputView.getManualPurchaseCount());
        List<List<Integer>> manualLottoNumbers = InputView.getManualLottoNumbers(purchaseType);
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(manualLottoNumbers, purchaseType,
                new RandomLottoNumberStrategy());

        OutputView.printPurchasedLottoTicketNumber(purchaseType);
        OutputView.printPurchasedLottoTickets(lottoTickets);

        return lottoTickets;
    }

    private void createResult(LottoMachine lottoMachine, List<LottoTicket> lottoTickets) {
        LottoTicketNumbers winningNumbers = LottoTicketNumbers.convertToLottoNumber(InputView.getWinningNumbers());
        LottoNumber bonusNumber = LottoNumber.createBonus(InputView.getBonusNumber(), winningNumbers);

        WinningStat winningStat = lottoMachine.createWinningStat(lottoTickets, winningNumbers, bonusNumber);
        OutputView.printWinningStat(winningStat, winningStat.calculateProfit(LOTTO_TICKET_PRICE));
    }
}
