package controller;

import static constant.LottoConstant.*;

import controller.dto.WinningStatDto;
import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoController {

    private final LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumberStrategy());

    public void run() {
        Money money = Money.from(InputView.getMoney());

        int manualCount = InputView.getManualCount();
        validateManualCount(money, manualCount);

        List<LottoTicket> lottoTickets = purchaseLottoTickets(money, manualCount);

        OutputView.printPurchasedLottoTicketNumber(manualCount, lottoTickets.size() - manualCount);
        OutputView.printPurchasedLottoTickets(lottoTickets);

        OutputView.printWinningStat(getWinningStatDto(lottoTickets));
    }

    private void validateManualCount(Money money, int manualCount) {
        if (!money.isPurchasable(manualCount * LOTTO_TICKET_PRICE)) {
            throw new IllegalArgumentException(INVALID_MANUAL_COUNT);
        }
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
