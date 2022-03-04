package lotto.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<Lotto> inputManualLottos(int maxAmount) {
        int amount = inputManualLottoAmount(maxAmount).getAmount();
        return inputManualLottoNumbers(amount);
    }

    private ManualLottoAmount inputManualLottoAmount(int maxAmount) {
        try {
            String number = InputView.inputManualLottoAmount();
            return new ManualLottoAmount(number, maxAmount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputManualLottoAmount(maxAmount);
        }
    }

    private List<Lotto> inputManualLottoNumbers(int amount) {
        InputView.printManualLottoGuideMesseage();
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            manualLottos.add(inputManualLottoNumber());
        }
        return manualLottos;
    }

    private Lotto inputManualLottoNumber() {
        try {
            String numbers = removeBlank(InputView.inputManualLottoNumber());
            return changeNumbersToLotto(numbers);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputManualLottoNumber();
        }
    }

    public Lottos createLottos(final List<Lotto> manualLottos, final int count) {
        Lottos lottos = new Lottos(manualLottos, count);
        OutputView.printLottos(manualLottos.size(), lottos);
        return lottos;
    }

    public LottoWinningNumbers createLottoWinningNumbers() {
        try {
            final String numbers = inputLottoWinningNumbers();
            final Lotto lotto = changeNumbersToLotto(numbers);
            final LottoNumber bonusNumber = new LottoNumber(inputBonusNumber());
            return new LottoWinningNumbers(lotto, bonusNumber);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createLottoWinningNumbers();
        }
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
        Result result = new Result(lottoWinningNumbers, lottos);
        OutputView.printWinningResult(result);
        return result;
    }
}
