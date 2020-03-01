package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoApplication {
    private static final List<LottosGenerator> lottosGenerators = Arrays.asList(new AutoLottos(), new ManualLottos());

    public static void main(String[] args) {
        LottoMoney lottoMoney = new LottoMoney(InputView.requestLottoMoneyInput());
        String lottoCountInput = InputView.requestManualLottoCount();
        List<String[]> manualLottos = InputView.requestManualLottoInput(Integer.parseInt(lottoCountInput));
        LottoCount lottoCount = new LottoCount(lottoMoney, lottoCountInput, manualLottos);

        Lottos lottos = createLottos(lottoCount);
        OutputView.printLottoCountAndLottos(lottoCount, lottos);

        WinningLotto winningLotto = createWinningLotto();

        MatchResults matchResults = lottos.toMatchResults(winningLotto);
        int earningsRate = calculateEarningsRate(matchResults, lottoMoney);
        OutputView.printLottoResultAndEarningsRate(matchResults, earningsRate);
    }

    private static Lottos createLottos(LottoCount lottoCount) {
        List<Lottos> lottos = lottosGenerators.stream()
                .map(element -> element.generate(lottoCount))
                .collect(Collectors.toList());
        return Lottos.combineAll(lottos);
    }

    private static WinningLotto createWinningLotto() {
        Lotto winningLottoLine = Lotto.from(InputView.requestWinningLottoInput());
        LottoNumber bonusNumber = LottoNumber.of(InputView.requestBonusNumberInput());
        return new WinningLotto(winningLottoLine, bonusNumber);
    }

    private static int calculateEarningsRate(MatchResults matchResults, LottoMoney lottoMoney) {
        int totalEarnings = matchResults.calculateTotalEarnings();
        return lottoMoney.calculateEarningsRate(totalEarnings);
    }
}
