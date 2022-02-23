package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final LottoMachine lottoMachine = new LottoMachine();

    public void run() {
        Money money = Money.from(InputView.getMoney());

        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTickets(money, new RandomLottoNumberStrategy());
        OutputView.printPurchasedLottoTicketNumber(lottoTickets.size());
        OutputView.printPurchasedLottoTickets(lottoTickets);

        LottoNumbers winningNumbers = new LottoNumbers(InputView.getWinningNumbers().stream()
                .map(LottoNumber::from)
                .collect(Collectors.toList()));
        LottoNumber bonusNumber = LottoNumber.from(InputView.getBonusNumber());

        WinningStat winningStat = lottoMachine.createWinningStat(winningNumbers, bonusNumber);
        OutputView.printWinningStat(winningStat);
    }
}
