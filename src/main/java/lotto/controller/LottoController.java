package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.domain.ManualLottoAmount;
import lotto.dto.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final String LOTTO_DELIMITER = ",";

    public Lottos createLottos(List<Lotto> lottos, final int count) {
        return new Lottos(lottos, count);
    }

    public LottoWinningNumbers createLottoWinningNumbers() {
        final String numbers = inputLottoWinningNumbers();
        final Lotto lotto = changeNumbersToLotto(numbers);
        final LottoNumber bonusNumber = new LottoNumber(inputBonusNumber());

        return new LottoWinningNumbers(lotto, bonusNumber);
    }

    public List<Lotto> inputManualLottos(int maxAmount) {
        int amount = inputManualLottoAmount(maxAmount).getAmount();
        if (amount == 0) {
            return new ArrayList<>();
        }
        InputView.printManualLottoGuideMesseage();
        return IntStream.range(0, amount)
                .mapToObj(i -> inputManualLottoNumbers())
                .map(s -> changeNumbersToLotto(s))
                .collect(Collectors.toList());
    }

    public ManualLottoAmount inputManualLottoAmount(int maxAmount) {
        String number = InputView.inputManualLottoAmount();
        return new ManualLottoAmount(number, maxAmount);
    }

    public String inputManualLottoNumbers() {
        return removeBlank(InputView.inputManualLottoNumbers());
    }

    private String inputLottoWinningNumbers() {
        return removeBlank(InputView.inputLottoWinningNumbers());
    }

    private String inputBonusNumber() {
        return InputView.inputBonusNumber();
    }

    private Lotto changeNumbersToLotto(String numbers) {
        List<LottoNumber> lottoNumbers = Arrays.stream(numbers.split(LOTTO_DELIMITER))
                .map((s) -> removeBlank(s))
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
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

    public void printLottos(int manualLottosAmount, Lottos lottos) {
        OutputView.printLottos(manualLottosAmount, lottos);
    }

    public void printWinningResult(Result result) {
        OutputView.printWinningResult(result);
    }
}
