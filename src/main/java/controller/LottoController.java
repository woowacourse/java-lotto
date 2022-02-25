package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Rank;
import domain.WinningLotto;
import domain.strategy.LottoNumberGenerateStrategy;
import domain.strategy.RandomLottoNumberGenerateStrategy;
import dto.LottoResultDto;
import factory.LottoFactory;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import repository.LottoNumberRepository;
import view.InputView;
import view.ResultView;
import vo.InputMoney;
import vo.TrialNumber;
import vo.WinningCount;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;
    private static final LottoNumberGenerateStrategy lottoNumberGenerator = new RandomLottoNumberGenerateStrategy();

    public void start() {
        InputMoney inputmoney = getInputMoney();
        TrialNumber trialNumber = getTrialNumberByInputMoney(inputmoney);
        InputView.printTrialNumber(trialNumber.getTrialNumber());

        Lottos autoLottos = LottoFactory.createAutoLottosByQuantity(trialNumber.getTrialNumber());
        InputView.printLottos(autoLottos);

        WinningLotto winningLotto = setupWinningLotto();
        Map<Rank, WinningCount> map = autoLottos.getResultByWinningLotto(winningLotto);
        ResultView.printResult(LottoResultDto.from(map, trialNumber));
    }

    private InputMoney getInputMoney() {
        try {
            return new InputMoney(InputView.scanInputMoney());
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return getInputMoney();
        }
    }

    private TrialNumber getTrialNumberByInputMoney(InputMoney inputMoney) {
        return new TrialNumber(inputMoney.getMoney() / LOTTO_PRICE);
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
