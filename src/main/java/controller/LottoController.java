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
        List<LottoTicket> lottoTickets = purchaseLottoTickets();
        createResult(lottoTickets);
    }

    private List<LottoTicket> purchaseLottoTickets() {
        Money money = Money.from(InputView.getMoney());
        int manualCount = InputView.getManualCount();

        validateManualCount(money, manualCount);

        List<LottoTicket> lottoTickets = lottoMachine.purchaseLottoTicketsByAuto(money);

        OutputView.printPurchasedLottoTicketNumber(lottoTickets.size());
        OutputView.printPurchasedLottoTickets(lottoTickets);

        return lottoTickets;
    }

    private void validateManualCount(Money money, int manualCount) {
        if (!money.isPurchasable(manualCount * LOTTO_TICKET_PRICE)) {
            throw new IllegalArgumentException(INVALID_MANUAL_COUNT);
        }
    }

    private void createResult(List<LottoTicket> lottoTickets) {
        LottoTicketNumbers winningNumbers = new LottoTicketNumbers(InputView.getWinningNumbers().stream()
                .map(LottoNumber::getInstance)
                .collect(Collectors.toList()));
        LottoNumber bonusNumber = LottoNumber.createBonus(InputView.getBonusNumber(), winningNumbers);

        WinningStat winningStat = lottoMachine.createWinningStat(lottoTickets, winningNumbers, bonusNumber);
        WinningStatDto winningStatDto = WinningStatDto.of(winningStat.getStat(),
            winningStat.calculateProfit(LOTTO_TICKET_PRICE));

        OutputView.printWinningStat(winningStatDto);
    }
}
