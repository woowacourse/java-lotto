package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.BonusNumber;
import lotto.model.LottoNumber;
import lotto.model.Lottos;
import lotto.model.WinningLotto;
import lotto.util.InputValidator;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    public void run() throws RuntimeException {
        Lottos lottos = makeLottos(InputView.inputPrice());
        ResultView.printResult(lottos);

        WinningLotto winningLotto = makeWinningLotto(InputView.inputWinningNumbers(),
                InputView.inputBonusNumber());

        winningLotto.checkRank(lottos);
        lottos.countRank();
        ResultView.printTotalResult(lottos);
    }

    private Lottos makeLottos(String money) throws RuntimeException {
        return new Lottos(InputValidator.validateMoney(money));
    }

    private WinningLotto makeWinningLotto(String winningNumbers, String bonusNumber) {
        List<LottoNumber> lottoNumbers = splitWinningNumbers(winningNumbers);
        BonusNumber lottoBonusNumber = new BonusNumber(toIntBonusNumber(bonusNumber));
        return new WinningLotto(lottoNumbers, lottoBonusNumber);
    }

    private List<LottoNumber> splitWinningNumbers(String winningNumbers) throws RuntimeException {
        String[] splitNumbers = InputValidator.validateLottoNumbers(winningNumbers);
        return Arrays.stream(splitNumbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private int toIntBonusNumber(String bonusNumber) throws RuntimeException {
        return InputValidator.validateNumber(bonusNumber);
    }
}
