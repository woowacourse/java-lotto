package controller;

import constants.LottoConstants;
import domain.InputMoney;
import domain.Lotto;
import domain.LottoNumber;
import domain.LottoQuantity;
import domain.Lottos;
import domain.Rank;
import domain.WinningCount;
import domain.WinningLotto;
import domain.strategy.LottoNumberGenerateStrategy;
import dto.LottoResultDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import view.InputView;
import view.ResultView;

public class LottoController {
    private final LottoNumberGenerateStrategy lottoNumberGenerator;

    public LottoController(LottoNumberGenerateStrategy lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public void start() {
        InputMoney inputMoney = getInputMoney();
        LottoQuantity lottoQuantity = getLottoQuantityByInputMoney(inputMoney);
        InputView.printLottoQuantity(lottoQuantity.getLottoQuantity());

        Lottos autoLottos = getRandomLottos(lottoQuantity);
        InputView.printLottos(autoLottos);

        WinningLotto winningLotto = setupWinningLotto();
        Map<Rank, WinningCount> lottoResult = autoLottos.getResultByWinningLotto(winningLotto);
        ResultView.printResult(LottoResultDto.from(lottoResult, lottoQuantity));
    }

    private LottoQuantity getLottoQuantityByInputMoney(InputMoney inputMoney) {
        return new LottoQuantity(inputMoney.getMoney() / LottoConstants.SINGLE_LOTTO_PRICE);
    }

    private Lottos getRandomLottos(LottoQuantity lottoQuantity) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoQuantity.getLottoQuantity(); i++) {
            lottos.add(new Lotto(getLottoNumbers()));
        }

        return new Lottos(lottos);
    }

    private Set<LottoNumber> getLottoNumbers() {
        return lottoNumberGenerator.generateLottoNumbers()
                .stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
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

            Set<LottoNumber> lottoNumbers = winningNumberValues.stream()
                    .map(LottoNumber::new)
                    .collect(Collectors.toSet());
            return new Lotto(lottoNumbers);
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return generateLotto();
        }
    }

    private LottoNumber generateBonusNumber() {
        try {
            return new LottoNumber(InputView.scanBonusNumber());
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return generateBonusNumber();
        }
    }

    private InputMoney getInputMoney() {
        try {
            return new InputMoney(InputView.scanInputMoney());
        } catch (IllegalArgumentException exception) {
            InputView.printException(exception);
            return getInputMoney();
        }
    }
}
