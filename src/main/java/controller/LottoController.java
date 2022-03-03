package controller;

import domain.InputMoney;
import domain.Lotto;
import domain.LottoNumber;
import domain.LottoQuantity;
import domain.Lottos;
import domain.WinningLotto;
import domain.WinningResult;
import domain.strategy.RandomLottoNumberGenerator;
import dto.LottoDto;
import dto.WinningResultDto;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;
import view.ResultView;

public class LottoController {
    public void start() {
        InputMoney inputMoney = generateInputMoney();
        LottoQuantity totalLottoQuantity = generateLottoQuantityByInputMoney(inputMoney);
        LottoQuantity manualLottoQuantity = generateManualLottoQuantity(inputMoney);
        LottoQuantity autoLottoQuantity = generateAutoLottoQuantity(totalLottoQuantity, manualLottoQuantity);

        Lottos manualLottos = generateManualLottos(manualLottoQuantity);
        printLottoQuantity(manualLottoQuantity, autoLottoQuantity);
        Lottos autoLottos = generateAutoLottosByLottoQuantity(autoLottoQuantity);
        runResultStep(manualLottos, autoLottos);
    }

    private InputMoney generateInputMoney() {
        try {
            return new InputMoney(InputView.scanInputMoney());
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateInputMoney();
        }
    }

    private LottoQuantity generateLottoQuantityByInputMoney(InputMoney inputMoney) {
        return new LottoQuantity(inputMoney);
    }

    private LottoQuantity generateManualLottoQuantity(InputMoney inputMoney) {
        try {
            int quantity = InputView.scanManualLottoQuantity();
            return LottoQuantity.createManual(quantity, inputMoney);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateManualLottoQuantity(inputMoney);
        }
    }

    private LottoQuantity generateAutoLottoQuantity(LottoQuantity totalLottoQuantity,
                                                    LottoQuantity manualLottoQuantity) {
        try {
            return totalLottoQuantity.subtract(manualLottoQuantity);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateAutoLottoQuantity(totalLottoQuantity, manualLottoQuantity);
        }
    }

    private Lottos generateManualLottos(LottoQuantity manualLottoQuantity) {
        try {
            List<Set<Integer>> lottoValues = InputView.scanManualLottoNumbers(manualLottoQuantity.getLottoQuantity());
            List<Lotto> manualLottoValues = lottoValues.stream()
                    .map(Lotto::new)
                    .collect(Collectors.toList());
            return new Lottos(manualLottoValues);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateManualLottos(manualLottoQuantity);
        }
    }

    private void printLottoQuantity(LottoQuantity manualLottoQuantity, LottoQuantity autoLottoQuantity) {
        OutputView.printLottoQuantity(
                manualLottoQuantity.getLottoQuantity(),
                autoLottoQuantity.getLottoQuantity()
        );
    }

    private Lottos generateAutoLottosByLottoQuantity(LottoQuantity lottoQuantity) {
        Lottos autoLottos = new Lottos(lottoQuantity, new RandomLottoNumberGenerator());
        printLottos(autoLottos);
        return autoLottos;
    }

    private void printLottos(Lottos autoLottos) {
        List<LottoDto> lottoDtos = getLottoDtosByLottos(autoLottos);
        OutputView.printLottos(lottoDtos);
    }

    private Lottos generateTotalLottos(Lottos manualLottos, Lottos autoLottos) {
        return Lottos.concat(manualLottos, autoLottos);
    }

    private WinningLotto generateWinningLotto() {
        Lotto lotto = generateLottoOfWinningLotto();
        LottoNumber bonusNumber = generateBonusNumberOfWinningLotto();

        try {
            return new WinningLotto(lotto, bonusNumber);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateWinningLotto();
        }
    }

    private List<LottoDto> getLottoDtosByLottos(Lottos autoLottos) {
        return autoLottos.getLottos()
                .stream()
                .map(LottoDto::new)
                .collect(Collectors.toList());
    }

    private Lotto generateLottoOfWinningLotto() {
        try {
            Set<Integer> numbers = InputView.scanWinningLottoNumbers();
            return new Lotto(numbers);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateLottoOfWinningLotto();
        }
    }

    private LottoNumber generateBonusNumberOfWinningLotto() {
        try {
            return new LottoNumber(InputView.scanBonusNumber());
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateBonusNumberOfWinningLotto();
        }
    }

    private void runResultStep(Lottos manualLottos, Lottos autoLottos) {
        Lottos totalLottos = generateTotalLottos(manualLottos, autoLottos);
        WinningLotto winningLotto = generateWinningLotto();
        WinningResult winningResult = generateWinningResultByLottosAndWinningLotto(totalLottos, winningLotto);
        printWinningResult(winningResult);
    }

    private WinningResult generateWinningResultByLottosAndWinningLotto(Lottos lottos, WinningLotto winningLotto) {
        return new WinningResult(lottos, winningLotto);
    }

    private void printWinningResult(WinningResult winningResult) {
        WinningResultDto winningResultDto = WinningResultDto.from(winningResult);
        ResultView.printResult(winningResultDto);
    }
}
