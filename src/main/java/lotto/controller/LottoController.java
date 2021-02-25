package lotto.controller;

import lotto.domain.Money;
import lotto.domain.lotto.LottoCount;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.result.LottoResult;
import lotto.domain.result.WinningLotto;
import lotto.utils.ParseUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.stream.Stream;

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
        LottoTickets lottoTickets = createManualLottoTickets(lottoCount.getManualLottoCount());
        lottoTickets.combine(createAutoTickets(lottoCount.getAutoLottoCount()));
        return lottoTickets;
    }

    private LottoTickets createManualLottoTickets(int manualCount) {
        OutputView.printInputManualLottoNumbers();
        return Stream.generate(() -> LottoTicket.manual(InputView.inputNumbers()))
                .limit(manualCount)
                .collect(collectingAndThen(toList(), LottoTickets::new));
    }

    private LottoTickets createAutoTickets(int autoCount) {
        return Stream.generate(LottoTicket::auto)
                .limit(autoCount)
                .collect(collectingAndThen(toList(), LottoTickets::new));
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
