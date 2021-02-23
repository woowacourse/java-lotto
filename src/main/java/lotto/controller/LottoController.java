package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    public void run() {
        Money money = new Money(InputView.inputMoney());
        ManualLottoAmount manualLottoAmount = new ManualLottoAmount(InputView.inputManualLottoAmount(), money.getPurchasableLottoCount());
        LottoTickets lottoTickets = getLottoTickets(money, manualLottoAmount);
        LottoTicket winningTicket = getWinningTicket(lottoTickets);
        LottoResult lottoResult = getLottoResult(lottoTickets, winningTicket);
        showResult(lottoResult, money);
    }

    private LottoTickets getLottoTickets(Money money, ManualLottoAmount manualLottoAmount) {
        List<LottoTicket> manualTickets = getManualLottoTickets(manualLottoAmount);
        return LottoTicketFactory.createLottoTicketsIncludingManualTickets(money, manualTickets);
    }

    private List<LottoTicket> getManualLottoTickets(ManualLottoAmount manualLottoAmount) {
        // TODO : 안내 메세지 출력
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        for (int i = 0; i < manualLottoAmount.getValue(); i++) {
            manualLottoTickets.add(
                    LottoTicketFactory.createManualLottoTicket(InputView.inputLottoNumbers()));
        }
        return manualLottoTickets;
    }

    private LottoTicket getWinningTicket(LottoTickets lottoTickets) {
        OutputView.printLottoTicketsCount(lottoTickets);
        OutputView.printLottoTickets(lottoTickets);
        return LottoTicketFactory.createManualLottoTicket(InputView.inputWinningNumbers());
    }

    private LottoResult getLottoResult(LottoTickets lottoTickets, LottoTicket winningTicket) {
        LottoNumber bonusNumber = LottoNumber.createLottoNumber(InputView.inputBonusNumber());
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        return new LottoResult(lottoTickets.checkWinningTickets(winningLotto));
    }

    private void showResult(LottoResult lottoResult, Money money) {
        OutputView.printResultStatistic(lottoResult);
        OutputView.printProfitRate(lottoResult, money);
    }
}
