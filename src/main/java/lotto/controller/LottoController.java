package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.dto.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final String LOTTO_DELIMITER = ",";

    public Lottos createLottos(final int count) {
        return new Lottos(count);
    }

    public void printLottos(Lottos lottos) {
        OutputView.printLottos(lottos);
    }

    public LottoWinningNumbers createLottoWinningNumbers() {
        final String numbers = inputLottoWinningNumbers();
        final Lotto lotto = new Lotto(changeNumbersToLotto(numbers));
        final LottoNumber bonusNumber = new LottoNumber(inputBonusNumber());

        return new LottoWinningNumbers(lotto, bonusNumber);
    }

    private String inputLottoWinningNumbers() {
        return removeBlank(InputView.inputLottoWinningNumbers());
    }

    private String inputBonusNumber() {
        return InputView.inputBonusNumber();
    }

    private List<LottoNumber> changeNumbersToLotto(String numbers) {
        return Arrays.stream(numbers.split(LOTTO_DELIMITER))
                .map((s) -> removeBlank(s))
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private String removeBlank(final String value) {
        return value.replace(" ", "");
    }

    public Result calculateResult(LottoWinningNumbers lottoWinningNumbers, Lottos lottos) {
        Result result = new Result();
        for (Lotto lotto : lottos.getLottos()) {
            result.calculateWinning(lottoWinningNumbers, lotto);
        }
        return result;
    }

    public void printWinningResult(Result result) {
        OutputView.printWinningResult(result);
    }
}
