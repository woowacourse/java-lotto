package lotto.controller;

import lotto.domain.Money;
import lotto.domain.lotto.*;
import lotto.domain.result.LottoResult;
import lotto.domain.result.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class LottoController {
    private final LottoTicketFactory lottoTicketFactory;

    public LottoController() {
        this.lottoTicketFactory = new LottoTicketFactory();
    }

    public void run() {
        Money inputMoney = new Money(InputView.inputMoney());
        int totalLottoCount = inputMoney.getPurchaseCount();
        LottoCount lottoCount = getLottoCount(totalLottoCount);
        List<LottoTicket> manualLottoTickets =
                createAllManualLottoTicket(lottoCount.getManualLottoCount());
        LottoTickets lottoTickets = lottoTicketFactory.generateLottoTickets(
                lottoCount.getAutoLottoCount(), manualLottoTickets);
        OutputView.printLottoTicketsCount(lottoCount);
        OutputView.printLottoTickets(lottoTickets);
        WinningLotto winningLotto = getWinningLotto();
        showResult(totalLottoCount, lottoTickets, winningLotto);
    }

    private List<LottoTicket> createAllManualLottoTicket(int manualLottoCount) {
        OutputView.printInputManualLottoNumbers();
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottoTickets.add(
                    InputView.inputNumbers()
                            .stream()
                            .map(LottoNumber::new)
                            .collect(collectingAndThen(toList(), LottoTicket::new)));
        }
        return manualLottoTickets;
    }

    private LottoCount getLottoCount(int totalLottoCount) {
        String inputManualLottoCount = InputView.inputManualLottoCount();
        return new LottoCount(inputManualLottoCount, totalLottoCount);
    }

    private WinningLotto getWinningLotto() {
        OutputView.printInputWinningNumbers();
        LottoTicket winningTicket = InputView.inputNumbers()
                .stream()
                .map(LottoNumber::new)
                .collect(collectingAndThen(toList(), LottoTicket::new));
        LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());
        return new WinningLotto(winningTicket, bonusNumber);
    }

    private void showResult(int totalLottoCount, LottoTickets lottoTickets, WinningLotto winningLotto) {
        LottoResult lottoResult = winningLotto.checkPrizes(lottoTickets);
        OutputView.printResultStatistic(lottoResult);
        Money totalProfit = lottoResult.getTotalProfit();
        double profitRate = totalProfit.divide(totalLottoCount);
        OutputView.printProfitRate(profitRate);
    }
}
