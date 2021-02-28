package lotto.controller;

import lotto.domain.*;
import lotto.service.LottoTicketService;
import lotto.service.LottoTicketsService;
import lotto.view.InputView;
import lotto.view.OutputView;

import static java.lang.Integer.parseInt;

public class LottoController {

    public void start() {
        Money money = initMoney();
        LottoTickets lottoTickets = initTickets(money);

        LottoTicket lottoWinnerTicket = initoWinnerTicket();
        LottoNumber lottoWinnerBonusNumber = initWinnerBonusNumber();
        LottoWinner lottoWinner = new LottoWinner(lottoWinnerTicket, lottoWinnerBonusNumber);

        OutputView.printRewardResultBoard();
        LottoResultStatistics lottoResultStatistics =
                LottoResultStatistics.apply(lottoTickets, lottoWinner);
        OutputView.printStatistics(lottoResultStatistics);
        OutputView.printFinalResult(lottoResultStatistics, money);
    }

    private Money initMoney() {
        Money money;
        try {
            OutputView.askHowMuchToBuy();
            money = new Money(InputView.getInput());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initMoney();
        }
        return money;
    }

    private LottoTickets initTickets(Money money) {
        LottoTickets manualLottoTickets = initManualTickets(money);
        LottoTickets autoLottoTickets = LottoTicketsService.createRandomTickets(money);
        OutputView.printHowManyTicketsBought(manualLottoTickets.size(), autoLottoTickets.size());
        LottoTickets mergedLottoTickets = manualLottoTickets.merge(autoLottoTickets);
        OutputView.printTickets(mergedLottoTickets);
        return mergedLottoTickets;
    }

    public LottoTickets initManualTickets(Money money) {
        OutputView.askHowManyManualTicketsToBuy();
        try {
            int manualTicketsCount = Integer.parseInt(InputView.getInput());
            OutputView.askManualTicketNumbers();
            return LottoTicketsService.createManualTickets(InputView.getManualTickets(manualTicketsCount), money);
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initManualTickets(money);
        }
    }

    private LottoTicket initoWinnerTicket() {
        OutputView.askWinnerTicket();
        try {
            return LottoTicketService.createManualTicket(InputView.getInput());
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initoWinnerTicket();
        }
    }

    private LottoNumber initWinnerBonusNumber() {
        OutputView.askWinnerBonusNumber();
        try {
            return new LottoNumber(parseInt(InputView.getInput()));
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initWinnerBonusNumber();
        }
    }
}