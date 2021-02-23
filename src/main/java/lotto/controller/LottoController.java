package lotto.controller;

import lotto.domain.Money;
import lotto.domain.lotto.*;
import lotto.domain.result.LottoResult;
import lotto.domain.result.WinningLotto;
import lotto.utils.ParseUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class LottoController {
    public void run() {
        Money inputMoney = new Money(InputView.inputMoney());
        int totalLottoCount = inputMoney.getPurchaseCount();
        LottoCount lottoCount = getLottoCount(totalLottoCount);
        LottoTickets lottoTickets = buyLottoTickets(lottoCount);
        OutputView.printLottoTicketsCount(lottoCount);
        OutputView.printLottoTickets(lottoTickets);
        WinningLotto winningLotto = readWinningLotto();
        showResult(totalLottoCount, lottoTickets, winningLotto);
    }

    private LottoCount getLottoCount(int totalLottoCount) {
        int inputManualCount = ParseUtil.parseInt(InputView.inputManualLottoCount());
        return new LottoCount(inputManualCount, totalLottoCount);
    }

    private LottoTickets buyLottoTickets(LottoCount lottoCount) {
        List<LottoTicket> lottoTicketGroup = new ArrayList<>();
        lottoTicketGroup.addAll(createAllManualLottoTicket(lottoCount.getManualLottoCount()));
        lottoTicketGroup.addAll(createAllAutoLottoTicket(lottoCount.getAutoLottoCount()));
        return new LottoTickets(lottoTicketGroup);
    }

    private List<LottoTicket> createAllManualLottoTicket(int manualLottoCount) {
        OutputView.printInputManualLottoNumbers();
        List<LottoTicket> manualLottoTickets = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            manualLottoTickets.add(
                    InputView.inputNumbers()
                            .stream()
                            .map(input -> LottoNumber.valueOf(ParseUtil.parseInt(input)))
                            .collect(collectingAndThen(toList(), LottoTicket::new)));
        }
        return manualLottoTickets;
    }

    private List<LottoTicket> createAllAutoLottoTicket(int autoLottoCount) {
        return IntStream.range(0, autoLottoCount)
                .mapToObj(i -> LottoTicketFactory.generateAutoLottoTicket())
                .collect(Collectors.toList());
    }

    private WinningLotto readWinningLotto() {
        OutputView.printInputWinningNumbers();
        LottoTicket winningTicket = InputView.inputNumbers()
                .stream()
                .map(input -> LottoNumber.valueOf(ParseUtil.parseInt(input)))
                .collect(collectingAndThen(toList(), LottoTicket::new));
        LottoNumber bonusNumber = LottoNumber.valueOf(ParseUtil.parseInt(InputView.inputBonusNumber()));
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
