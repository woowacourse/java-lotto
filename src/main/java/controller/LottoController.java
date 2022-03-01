package controller;

import static domain.Lotto.LOTTO_PRICE;
import static domain.LottoResultHandler.getLottoResultDto;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoNumber;
import domain.LottoNumberRepository;
import domain.Lottos;
import domain.WinningLotto;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.ResultView;
import vo.InputMoney;
import vo.ManualLottoQuantity;
import vo.NumberOfLottos;

public class LottoController {

    public void start() {
        InputMoney inputmoney = getInputMoney();
        ManualLottoQuantity manualLottoQuantity = getManualLottoQuantity();
        NumberOfLottos numberOfLottos = getNumberOfLottosByInputMoney(inputmoney);
        InputView.printTrialNumber(numberOfLottos.getNumberOfLottos());

        Lottos autoLottos = LottoFactory.createAutoLottosByQuantity(numberOfLottos.getNumberOfLottos());
        InputView.printLottos(autoLottos);

        WinningLotto winningLotto = setupWinningLotto();
        ResultView.printResult(getLottoResultDto(winningLotto, autoLottos));
    }

    private InputMoney getInputMoney() {
        try {
            return new InputMoney(InputView.scanInputMoney());
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return getInputMoney();
        }
    }

    private ManualLottoQuantity getManualLottoQuantity() {
        try {
            return new ManualLottoQuantity(InputView.scanManualLottoQuantity());
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return getManualLottoQuantity();
        }
    }

    private NumberOfLottos getNumberOfLottosByInputMoney(InputMoney inputMoney) {
        return new NumberOfLottos(inputMoney.getMoney() / LOTTO_PRICE);
    }

    private WinningLotto setupWinningLotto() {
        Lotto lotto = generateLotto();
        LottoNumber bonusNumber = generateBonusNumber();

        try {
            return new WinningLotto(lotto, bonusNumber);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return setupWinningLotto();
        }
    }

    private Lotto generateLotto() {
        try {
            List<Integer> winningNumberValues = InputView.scanWinningNumbers();

            List<LottoNumber> lottoNumbers = winningNumberValues.stream()
                    .map(LottoNumberRepository::getLottoNumberByInt)
                    .collect(Collectors.toList());
            return new Lotto(lottoNumbers);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return generateLotto();
        }
    }

    private LottoNumber generateBonusNumber() {
        try {
            int bonusNumberValue = InputView.scanBonusNumber();
            return LottoNumberRepository.getLottoNumberByInt(bonusNumberValue);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return generateBonusNumber();
        }
    }
}
