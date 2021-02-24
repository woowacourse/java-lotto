package lotto.controller;

import lotto.domain.LottoResultStatistics;
import lotto.domain.ManualAmount;
import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.LottoTickets;
import lotto.domain.lottos.winnerlotto.LottoBonusNumber;
import lotto.domain.lottos.winnerlotto.LottoWinner;
import lotto.domain.money.Money;
import lotto.service.LottoTicketService;
import lotto.service.LottoTicketsService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void lottoStart() {
        Money money = initMoney();
        ManualAmount manualAmount = initManualCount(money);
        LottoTickets lottoTickets = initLottoTickets(money, manualAmount);

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

    private ManualAmount initManualCount(Money money) {
        try {
            return new ManualAmount(InputView.getManualCountInput(), money);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initManualCount(money);
        }
    }

    private LottoTickets initLottoTickets(Money money, ManualAmount manualAmount) {
        try {
            LottoTickets lottoTickets = LottoTicketsService.createLottoTickets(money, InputView.getManualNumbersInput(manualAmount));
            OutputView.printTickets(lottoTickets, money.getLottoCount(), manualAmount);
            return lottoTickets;
        } catch (NullPointerException | IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return initLottoTickets(money, manualAmount);
        }
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