package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {
    private static final String DELIMITER = ", ";

    public void start() {
        Money money = new Money(InputView.askInputMoney());
        int manualLottoCount = InputView.askManualLottoCount();
        int autoLottoCount = money.getAutoLottoCount(manualLottoCount);
        List<String> manualLottoNumbers = InputView.askManualLottoNumbers(manualLottoCount);
        Lottos manualLottos = createManualLottos(manualLottoNumbers);

        OutputView.printCountOfLotto(autoLottoCount, manualLottoCount);
        Lottos totalLottos = getLottos(autoLottoCount, manualLottos);
        WinningLotto winningNumber = inputWinningNumber();
        getStatistics(totalLottos, winningNumber, money);
    }

    private Lottos getLottos(int autoLottoCount, Lottos manualLottos) {
        Lottos autoLottos = Lottos.generateAutoLottos(autoLottoCount);
        Lottos totalLottos = manualLottos.concat(autoLottos);
        OutputView.printLottos(totalLottos);
        return totalLottos;
    }

    private Lottos createManualLottos(List<String> manualLottoNumbers) {
        List<Lotto> lottos = manualLottoNumbers.stream()
                .map(LottoController::generateManualLotto)
                .collect(Collectors.toList());
        return new Lottos(lottos);
    }

    private WinningLotto inputWinningNumber() {
        String inputWinningNumber = InputView.askInputWinningNumber();
        Lotto winningNumber = generateManualLotto(inputWinningNumber);
        LottoNumber bonusBall = LottoNumber.of(InputView.askInputBonusBall());
        return new WinningLotto(winningNumber, bonusBall);
    }

    private static Lotto generateManualLotto(String input) {
        String[] numbers = input.split(DELIMITER);
        LottoGenerator lottoGenerator = new ManualLottoGenerator(numbers);
        return lottoGenerator.generateLotto();
    }

    private void getStatistics(Lottos lottos, WinningLotto winningNumber, Money money) {
        Statistic winningStatistics = lottos.getWinningStatistics(winningNumber);
        OutputView.printStatistics(winningStatistics);
        OutputView.printProfitRate(winningStatistics, money);
    }
}
