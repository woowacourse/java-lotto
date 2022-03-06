package controller;

import domain.InputMoney;
import domain.Lotto;
import domain.LottoGame;
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
        LottoQuantity manualLottoQuantity = generateManualLottoQuantity(inputMoney);
        Lottos manualLottos = generateManualLottos(manualLottoQuantity);

        LottoGame lottoGame = new LottoGame(LottoQuantity.from(inputMoney), manualLottoQuantity, manualLottos);
        printLottoQuantity(lottoGame.getManualLottoQuantity(), lottoGame.getAutoLottoQuantity());
        printLottos(lottoGame.createAutoLottos(new RandomLottoNumberGenerator()));
        printWinningResult(lottoGame.createWinningResult(generateWinningLotto()));
    }

    private InputMoney generateInputMoney() {
        try {
            return new InputMoney(InputView.scanInputMoney());
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateInputMoney();
        }
    }

    private LottoQuantity generateManualLottoQuantity(InputMoney inputMoney) {
        try {
            int quantity = InputView.scanManualLottoQuantity();
            return LottoQuantity.of(quantity, inputMoney);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateManualLottoQuantity(inputMoney);
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

    private void printLottos(Lottos lottos) {
        List<LottoDto> lottoDtos = getLottoDtosByLottos(lottos);
        OutputView.printLottos(lottoDtos);
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

    private List<LottoDto> getLottoDtosByLottos(Lottos lottos) {
        return lottos.getLottos()
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

    private void printWinningResult(WinningResult winningResult) {
        WinningResultDto winningResultDto = WinningResultDto.from(winningResult);
        ResultView.printResult(winningResultDto);
    }
}
