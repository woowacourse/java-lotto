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
        List<List<LottoNumber>> lottoNumbersGroup = new ArrayList<>();
        lottoNumbersGroup.addAll(createAllManualLottoTicket(lottoCount.getManualLottoCount()));
        lottoNumbersGroup.addAll(createAutoNumbers(lottoCount.getAutoLottoCount()));
        return new LottoTickets(lottoNumbersGroup);
    }

    private List<List<LottoNumber>> createAllManualLottoTicket(int manualLottoCount) {
        OutputView.printInputManualLottoNumbers();
        return IntStream.range(0, manualLottoCount)
                .mapToObj(i -> InputView.inputNumbers()
                        .stream()
                        .map(input -> LottoNumber.valueOf(ParseUtil.parseInt(input)))
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private List<List<LottoNumber>> createAutoNumbers(int autoLottoCount) {
        return IntStream.range(0, autoLottoCount)
                .mapToObj(i -> AutoNumbersFactory.generateAutoLottoTicket())
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

    private void showResult(int totalLottoCount, LottoTickets lottoTickets,
                            WinningLotto winningLotto) {
        LottoResult lottoResult = winningLotto.checkPrizes(lottoTickets);
        OutputView.printResultStatistic(lottoResult);
        Money totalProfit = lottoResult.getTotalProfit();
        double profitRate = totalProfit.getProfitRate(totalLottoCount);
        OutputView.printProfitRate(profitRate);
    }
}
