package controller;

import domain.*;
import domain.strategy.RandomLottoNumberStrategy;
import view.InputView;
import view.OutputView;

import java.util.List;

import static constant.LottoConstant.LOTTO_TICKET_PRICE;

public class LottoController {

    public void run() {
        LottoMachine lottoMachine = new LottoMachine();
        List<LottoTicket> lottoTickets = purchaseLottoTickets(lottoMachine);
        createResult(lottoMachine, lottoTickets);
    }

    private List<LottoTicket> purchaseLottoTickets(LottoMachine lottoMachine) {
        Money money = Money.from(InputView.getMoney());
        lottoMachine.validateMoney(money);
        int manualCount = calculateManualCount(money);
        List<List<Integer>> lottoNumbers = getLottoNumbers(money, manualCount);
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(money, lottoNumbers,
                new RandomLottoNumberStrategy());

        OutputView.printPurchasedLottoTicketNumber(lottoTickets.size(), manualCount);
        OutputView.printPurchasedLottoTickets(lottoTickets);

        return lottoTickets;
    }

    private List<List<Integer>> getLottoNumbers(Money money, int manualCount) {
        money.validateManualCount(manualCount);
        return InputView.getManualLottoNumbers(manualCount);
    }

    private int calculateManualCount(Money money) {
        return InputView.getManualPurchaseCount();
    }

    private void createResult(LottoMachine lottoMachine, List<LottoTicket> lottoTickets) {
        LottoTicketNumbers winningNumbers = LottoTicketNumbers.convertToLottoNumber(InputView.getWinningNumbers());
        LottoNumber bonusNumber = LottoNumber.createBonus(InputView.getBonusNumber(), winningNumbers);

        WinningStat winningStat = lottoMachine.createWinningStat(lottoTickets, winningNumbers, bonusNumber);
        OutputView.printWinningStat(winningStat, winningStat.calculateProfit(LOTTO_TICKET_PRICE));
    }
}
