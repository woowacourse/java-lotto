package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoMoney;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.ResultStatistic;
import lotto.domain.WinningInformation;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ManualLottoController {

    public static void run() {
        try {
            runWithoutExceptionCatch();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
        }
    }

    private static void runWithoutExceptionCatch() {
        LottoMoney money = new LottoMoney(InputView.getMoneyForLotto());
        LottoCount lottoCount = new LottoCount(money, InputView.getManualLottoAmount());
        Lottos lottos = readLottos(lottoCount);

        printPurchasedLottos(lottoCount, lottos);

        WinningInformation winningInformation = readWinningInformation();

        ResultStatistic result = ResultStatistic.calculate(lottos, winningInformation);
        OutputView.printResultStatistic(result, money.getMoney());
    }

    private static Lottos readLottos(LottoCount lottoCount) {
        return Lottos.createLottos(
            lottoCount,
            InputView.getManualLottos(lottoCount.getManualLottoCountByLong())
        );
    }

    private static void printPurchasedLottos(LottoCount lottoCount, Lottos lottos) {
        OutputView.printPurchasedLottos(
            lottoCount.getManualLottoCountByLong(),
            lottoCount.getAutoLottoCountByLong(),
            lottos
        );
    }

    private static WinningInformation readWinningInformation() {
        Lotto winningLotto = Lotto.createLottoManual(InputView.getWinningLotto());
        LottoNumber bonus = LottoNumber.of(InputView.getBonusLottoNumber());
        return new WinningInformation(winningLotto, bonus);
    }
}
