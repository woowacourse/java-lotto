package controller;

import static constant.LottoConstant.*;

import controller.dto.WinningStatDto;
import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberStrategy());

    public void run() {
        Money money = Money.from(InputView.getMoney());

        LottoCount lottoCount = LottoCount.of(InputView.getManualCount(), money);

        List<LottoTicket> lottoTickets = purchaseLottoTickets(money, lottoCount.getManualCount());

        OutputView.printPurchasedLottoTicketNumber(lottoCount.getManualCount(), lottoCount.getAutoCount());
        OutputView.printPurchasedLottoTickets(lottoTickets);

        OutputView.printWinningStat(getWinningStatDto(lottoTickets));
    }

    private List<LottoTicket> purchaseLottoTickets(Money money, int manualCount) {
        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTicketsByManual(
            InputView.getManualNumbers(manualCount));

        lottoTickets.addAll(
            lottoMachine.purchaseLottoTicketsByAuto(money.consume(manualCount * LOTTO_TICKET_PRICE)));

        return lottoTickets;
    }

    private WinningStatDto getWinningStatDto(List<LottoTicket> lottoTickets) {
        LottoTicketNumbers winningNumbers = new LottoTicketNumbers(InputView.getWinningNumbers().stream()
            .map(LottoNumber::getInstance)
            .collect(Collectors.toList()));

        LottoNumber bonusNumber = LottoNumber.createBonus(InputView.getBonusNumber(), winningNumbers);

        WinningStat winningStat = lottoMachine.createWinningStat(lottoTickets, winningNumbers, bonusNumber);

        return WinningStatDto.of(winningStat.getStat(), winningStat.calculateProfit(LOTTO_TICKET_PRICE));
    }
}
