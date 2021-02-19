package lotto.controller;

import lotto.domain.*;
import lotto.service.LottoTicketService;
import lotto.service.LottoTicketsService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void lottoStart() {
        Money money = initMoney();
        LottoTickets lottoTickets = initLottoTickets(money);

        LottoWinnerTicket lottoWinnerTicket = initLottoWinnerTicket();
        LottoWinnerBonusNumber lottoWinnerBonusNumber = initLottoWinnerBonusNumber(lottoWinnerTicket);
        LottoWinner lottoWinner = new LottoWinner(lottoWinnerTicket, lottoWinnerBonusNumber);

        OutputView.printRewardResultBoard();
        LottoResultStatistics lottoResultStatistics =
                LottoResultStatistics.calculateResultStatistics(lottoTickets, lottoWinner);
        OutputView.printStatistics(lottoResultStatistics);
        OutputView.printFinalResult(lottoResultStatistics, money);
    }

    private Money initMoney() {
        Money money;
        try {
            OutputView.askHowMuchToBuy();
            money = new Money(InputView.getUserInput());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initMoney();
        }
        OutputView.howMuchBought(money.getLottoCount());
        return money;
    }

    private LottoTickets initLottoTickets(Money money) {
        LottoTickets lottoTickets = LottoTicketsService.createLottoTickets(money);
        OutputView.printTickets(lottoTickets);
        return lottoTickets;
    }

    private LottoWinnerTicket initLottoWinnerTicket() {
        OutputView.askWinnerLottoTicket();
        try {
            return LottoTicketService.createLottoWinnerTicket(InputView.getUserInput());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initLottoWinnerTicket();
        }
    }

    private LottoWinnerBonusNumber initLottoWinnerBonusNumber(LottoWinnerTicket lottoWinnerTicket) {
        OutputView.askWinnerBonusNumber();
        try {
            return new LottoWinnerBonusNumber(Integer.parseInt(InputView.getUserInput()), lottoWinnerTicket);
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initLottoWinnerBonusNumber(lottoWinnerTicket);
        }
    }
}