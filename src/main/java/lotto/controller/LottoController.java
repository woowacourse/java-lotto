package lotto.controller;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.exception.InvalidException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public static final String LOTTO_DELIMITER = ",";
    private Lottos lottos;
    private LottoWinningNumbers lottoWinningNumbers;

    public LottoController() {
    }

    public void printLottos() {
        OutputView.printLottos(lottos);
    }

    public void inputLottoMoney(final int money) {
        lottos = new Lottos(money);
    }

    public void createLottoWinningNumbers() {
        String value = inputLottoWinningNumbers();
        int bonusNumber = inputBonusNumber();

        lottoWinningNumbers = new LottoWinningNumbers(value, bonusNumber);
    }

    private String inputLottoWinningNumbers() {
        String value = removeBlank(InputView.inputLottoWinningNumbers());
        checkInputLottoWinningNumbers(value);

        return value;
    }

    private static void checkInputLottoWinningNumbers(final String numbers) {
        checkDelimiterCount(numbers);
        checkCreateLottoWinningNumbers(numbers);
        checkNotInteger(numbers);
        checkIntegerRange(numbers);
        checkDuplicateNumber(numbers);
    }

    private static void checkDelimiterCount(final String numbers) {
        if (numbers.chars()
                .filter(c -> c == LOTTO_DELIMITER.charAt(0))
                .count() != LottoNumber.LOTTO_SIZE - 1) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private static void checkCreateLottoWinningNumbers(final String numbers) {
        try {
            numbers.split(LOTTO_DELIMITER);
        } catch (java.lang.Exception e) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private static void checkNotInteger(final String numbers) {
        String[] values = numbers.split(LOTTO_DELIMITER);
        try {
            Arrays.stream(values)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (java.lang.Exception e) {
            throw new IllegalArgumentException(InvalidException.ERROR_NOT_INTEGER);
        }
    }

    private static void checkIntegerRange(final String numbers) {
        String[] values = numbers.split(LOTTO_DELIMITER);
        if (!Arrays.stream(values)
                .map(Integer::parseInt)
                .allMatch(number -> LottoNumber.LOTTO_MIN_RANGE <= number
                        && number <= 45)) {
            throw new IllegalArgumentException(InvalidException.ERROR_INTEGER_RANGE);
        }
    }

    private static void checkDuplicateNumber(final String numbers) {
        String[] values = numbers.split(LOTTO_DELIMITER);
        if (LottoNumber.LOTTO_SIZE != Set.copyOf(Arrays.asList(values)).size()) {
            throw new IllegalArgumentException(InvalidException.ERROR_CREATE_LOTTO);
        }
    }

    private String removeBlank(final String value) {
        return value.replace(" ", "");
    }

    public int inputBonusNumber() {
        String bonusNumber = InputView.inputBonusNumber();
        checkValidateInt(bonusNumber);

        return Integer.parseInt(bonusNumber);
    }

    private static void checkValidateInt(final String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(InvalidException.ERROR_WRONG_INPUT_MONEY);
        }
    }

    public void calculateRanks() {
        lottoWinningNumbers.initWinningResult();

        for (Lotto lotto : lottos.getLottos()) {
            lottoWinningNumbers.calculateWinning(lotto);
        }
    }

    public double calculateProfit(final int money) {
        return (double) lottoWinningNumbers.calculateWinningMoney() / money;
    }

    public void printWinningResult() {
        OutputView.printWinningResult(lottoWinningNumbers);
    }

    public void printProfit(final double profit) {
        OutputView.printProfit(profit);
        if (profit >= 1){
            OutputView.printWinningLottoProfit();
            return;
        }
        OutputView.printWinningLottoLoss();
    }
}
