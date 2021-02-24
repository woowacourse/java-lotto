package lotto.controller;

import lotto.domain.LottoResultStatistics;
import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.LottoTickets;
import lotto.domain.lottos.winnerlotto.LottoWinner;
import lotto.domain.lottos.winnerlotto.LottoBonusNumber;
import lotto.domain.money.Money;
import lotto.service.LottoTicketService;
import lotto.service.LottoTicketsService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void lottoStart() {
        Money money = initMoney();
        LottoTickets lottoTickets = initLottoTickets(money);

        LottoTicket lottoWinnerTicket = initLottoWinnerTicket();
        LottoBonusNumber lottoBonusNumber = initLottoWinnerBonusNumber(lottoWinnerTicket);
        LottoWinner lottoWinner = new LottoWinner(lottoWinnerTicket, lottoBonusNumber);

        LottoResultStatistics lottoResultStatistics =
                LottoResultStatistics.calculateResultStatistics(lottoTickets, lottoWinner);
        OutputView.printRewardResultBoard(lottoResultStatistics);
        OutputView.printFinalResult(lottoResultStatistics, money);
    }

    private Money initMoney() {
        try {
            return new Money(InputView.getMoneyInput());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initMoney();
        }
    }

    private LottoTickets initLottoTickets(Money money) {
        LottoTickets lottoTickets = LottoTicketsService.createLottoTickets(money);
        OutputView.printTickets(lottoTickets, money.getLottoCount());
        return lottoTickets;
    }

    private LottoTicket initLottoWinnerTicket() {
        try {
            return LottoTicketService.createManualLottoTicket(InputView.getWinnerNumbersInput());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initLottoWinnerTicket();
        }
    }

    private LottoBonusNumber initLottoWinnerBonusNumber(LottoTicket lottoWinnerTicket) {
        try {
            return LottoBonusNumber.of(InputView.getBonusNumberInput(), lottoWinnerTicket);
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initLottoWinnerBonusNumber(lottoWinnerTicket);
        }
    }
}