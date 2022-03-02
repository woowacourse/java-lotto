package controller;

import static constant.LottoConstant.*;

import controller.dto.LottoTicketDto;
import controller.dto.WinningStatDto;
import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    private final LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberStrategy());

    public void run() {
        Money totalMoney = Money.from(InputView.getMoney());

        Money moneyOfManual = Money.from(InputView.getManualCount() * LOTTO_TICKET_PRICE);
        Money moneyOfAuto = totalMoney.consume(moneyOfManual);

        List<LottoTicket> lottoTickets = purchaseLottoTickets(moneyOfManual, moneyOfAuto);

        OutputView.printPurchasedLottoTicketNumber(
            LottoTicketDto.of(lottoTickets,
                moneyOfManual.getPurchasableNumber(LOTTO_TICKET_PRICE),
                moneyOfAuto.getPurchasableNumber(LOTTO_TICKET_PRICE)));
        OutputView.printWinningStat(getWinningStatDto(lottoTickets));
    }

    private List<LottoTicket> purchaseLottoTickets(Money moneyOfManual, Money moneyOfAuto) {

        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTicketsByManual(
            InputView.getManualNumbers(moneyOfManual.getPurchasableNumber(LOTTO_TICKET_PRICE)));

        lottoTickets.addAll(
            lottoMachine.purchaseLottoTicketsByAuto(moneyOfAuto));

        return lottoTickets;
    }

    private WinningStatDto getWinningStatDto(List<LottoTicket> lottoTickets) {
        LottoTicketNumbers winningNumbers = new LottoTicketNumbers(InputView.getWinningNumbers().stream()
            .map(LottoNumber::getInstance)
            .collect(Collectors.toList()));

        LottoNumber bonusNumber = LottoNumber.createBonus(InputView.getBonusNumber(), winningNumbers);

        return WinningStatDto
            .from(lottoMachine.createWinningStat(lottoTickets, winningNumbers, bonusNumber));
    }
}
