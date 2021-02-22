package lotto.controller;

import lotto.domain.*;
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
        String inputManualLottoCount = InputView.inputManualLottoCount();
        int totalLottoCount = inputMoney.getPurchaseCount();
        LottoCount lottoCount = new LottoCount(inputManualLottoCount, totalLottoCount);
        List<LottoTicket> manualLottoNumbers = new ArrayList<>();
        OutputView.printInputManualLottoNumbers();
        while (lottoCount.isAvailManualLottoRound()) {
            List<String> numbers = InputView.inputManualLottoNumbers();
            manualLottoNumbers.add(numbers.stream()
                    .map(LottoNumber::new)
                    .collect(collectingAndThen(toList(), LottoTicket::new)));
            lottoCount.passManualLottoRound();
        }
        LottoTickets manualLottoTickets = new LottoTickets(manualLottoNumbers);
        LottoTickets lottoTickets = lottoTicketFactory.generateLottoTickets(lottoCount.getAutoLottoCount());
        OutputView.printLottoTicketsCount(lottoCount);
        OutputView.printLottoTickets(manualLottoTickets);
        OutputView.printLottoTickets(lottoTickets);
        WinningLotto winningLotto = getWinningLotto(lottoTickets);
        showResult(lottoTickets, winningLotto);
    }

    private WinningLotto getWinningLotto(LottoTickets lottoTickets) {
        LottoTicket winningTicket = InputView.inputWinningNumbers().stream()
                .map(LottoNumber::new)
                .collect(collectingAndThen(toList(), LottoTicket::new));
        LottoNumber bonusNumber = new LottoNumber(InputView.inputBonusNumber());
        return new WinningLotto(winningTicket, bonusNumber);
    }

    private void showResult(LottoTickets lottoTickets, WinningLotto winningLotto) {
        LottoResult lottoResult = winningLotto.checkPrizes(lottoTickets);
        OutputView.printResultStatistic(lottoResult);
        Money totalProfit = lottoResult.getTotalProfit();
        double profitRate = totalProfit.divide(lottoResult.lottoResult().size());
        OutputView.printProfitRate(profitRate);
    }
}
