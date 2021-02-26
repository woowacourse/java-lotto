package lotto.controller;

import lotto.domain.*;
import lotto.service.LottoTicketService;
import lotto.service.LottoTicketsService;
import lotto.view.InputView;
import lotto.view.OutputView;

import static java.lang.Integer.parseInt;

public class LottoController {

    public void lottoStart() {
        Money money = initMoney();
        LottoTickets lottoTickets = initLottoTickets(money);

        LottoTicket lottoWinnerTicket = initLottoWinnerTicket();
        LottoNumber lottoWinnerBonusNumber = initLottoWinnerBonusNumber();
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
        OutputView.howMuchBought(money.lottoCount());
        return money;
    }

    private LottoTickets initLottoTickets(Money money) {
        LottoTickets manualLottoTickets = initManualLottoTickets(money);
        LottoTickets autoLottoTickets = LottoTicketsService.createAutoLottoTickets(money);
        LottoTickets mergedLottoTickets = LottoTicketsService.mergeLottoTickets(manualLottoTickets, autoLottoTickets);
        OutputView.printTickets(mergedLottoTickets);
        return mergedLottoTickets;
    }

    public LottoTickets initManualLottoTickets(Money money) {
        OutputView.askHowManyManualLottoTicketsToBuy();
        try {
            int manualTicketsCount = Integer.parseInt(InputView.getUserInput());
            OutputView.askManualLottoTicket();
            return LottoTicketsService.createManualLottoTickets(money, manualTicketsCount);
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initManualLottoTickets(money);
        }
    }

    private LottoTicket initLottoWinnerTicket() {
        OutputView.askWinnerLottoTicket();
        try {
            return LottoTicketService.createManualLottoTicket(InputView.getUserInput());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initLottoWinnerTicket();
        }
    }

    private LottoNumber initLottoWinnerBonusNumber() {
        OutputView.askWinnerBonusNumber();
        try {
            return new LottoNumber(parseInt(InputView.getUserInput()));
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initLottoWinnerBonusNumber();
        }
    }
}