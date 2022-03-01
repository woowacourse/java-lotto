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
        LottoQuantity lottoQuantity = generateLottoQuantityByInputMoney(inputMoney);
        printLottoQuantity(lottoQuantity);

        Lottos autoLottos = generateAutoLottosByLottoQuantity(lottoQuantity);
        printLottos(autoLottos);

        WinningLotto winningLotto = generateWinningLotto();
        WinningResult winningResult = generateWinningResultByLottosAndWinningLotto(autoLottos, winningLotto);
        printWinningResult(winningResult, lottoQuantity);
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

    private void printLottoQuantity(LottoQuantity lottoQuantity) {
        OutputView.printLottoQuantity(lottoQuantity.getLottoQuantity());
    }

    private Lottos generateAutoLottosByLottoQuantity(LottoQuantity lottoQuantity) {
        return new Lottos(lottoQuantity, new RandomLottoNumberGenerator());
    }

    private void printLottos(Lottos autoLottos) {
        List<LottoDto> lottoDtos = getLottoDtosByLottos(autoLottos);
        OutputView.printLottos(lottoDtos);
    }

    private List<LottoDto> getLottoDtosByLottos(Lottos autoLottos) {
        return autoLottos.getLottos()
                .stream()
                .map(LottoDto::new)
                .collect(Collectors.toList());
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

    private Lotto generateLottoOfWinningLotto() {
        try {
            List<Integer> winningNumberValues = InputView.scanWinningNumbers();
            Set<LottoNumber> lottoNumbers = numbersToLottoNumberSet(winningNumberValues);
            return new Lotto(lottoNumbers);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateLottoOfWinningLotto();
        }
    }

    private Set<LottoNumber> numbersToLottoNumberSet(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    private LottoNumber generateBonusNumberOfWinningLotto() {
        try {
            return new LottoNumber(InputView.scanBonusNumber());
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return generateBonusNumberOfWinningLotto();
        }
    }

    private WinningResult generateWinningResultByLottosAndWinningLotto(Lottos autoLottos, WinningLotto winningLotto) {
        return autoLottos.getWinningResultByWinningLotto(winningLotto);
    }

    private void printWinningResult(WinningResult winningResult, LottoQuantity lottoQuantity) {
        WinningResultDto winningResultDto = WinningResultDto.from(winningResult);
        ResultView.printResult(winningResultDto);
    }
}
