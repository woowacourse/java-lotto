package lotto.controller;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lotto.model.LottoMachine;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    private static final String MONEY_ERROR_MESSAGE = "[ERROR] 유효한 입력이 아닙니다.";
    private static final String NOT_NUMBER_ERROR_MESSAGE = "[ERROR] 문자가 입력되었습니다.";

    private static final String NUMBER_REGEX = "\\d+";

    public LottoMachine makeLottoMachine() {
        Lottos lottos = makeAndPrintLottos();
        WinningLotto winningLotto = makeWinningLotto(InputView.inputWinningNumbers(),
                InputView.inputBonusNumber());
        return new LottoMachine(lottos, winningLotto);
    }

    private Lottos makeAndPrintLottos() {
        Lottos lottos = makeLottos(InputView.inputMoney());
        ResultView.printBuyingLottosResult(lottos);
        return lottos;
    }

    private Lottos makeLottos(String money) throws RuntimeException {
        return new Lottos(validateMoney(money));
    }

    private Money validateMoney(String money) throws RuntimeException {
        if (!Pattern.matches(NUMBER_REGEX, money)) {
            throw new IllegalArgumentException(MONEY_ERROR_MESSAGE);
        }
        return new Money(Integer.parseInt(money));
    }

    private WinningLotto makeWinningLotto(String winningNumbers, String bonusNumber) throws RuntimeException {
        LottoNumbers lottoNumbers = splitWinningNumbers(winningNumbers);
        LottoNumber lottoBonusNumber = new LottoNumber(toIntBonusNumber(bonusNumber));
        return new WinningLotto(lottoNumbers, lottoBonusNumber);
    }

    private LottoNumbers splitWinningNumbers(String winningNumbers) throws RuntimeException {
        String[] splitNumbers = validateLottoNumbers(winningNumbers);
        return new LottoNumbers(Arrays.stream(splitNumbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private String[] validateLottoNumbers(String numbers) throws RuntimeException {
        String[] splitNumbers = numbers.split(",");
        for (String number : splitNumbers) {
            validateNumber(number);
        }
        return trimLottoNumbers(splitNumbers);
    }

    private int validateNumber(String number) throws RuntimeException {
        try {
            String trimNumber = number.trim();
            return Integer.parseInt(trimNumber);
        } catch (NumberFormatException e) {
            throw new RuntimeException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    private String[] trimLottoNumbers(String[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i].trim();
        }
        return numbers;
    }

    private int toIntBonusNumber(String bonusNumber) throws RuntimeException {
        return validateNumber(bonusNumber);
    }
}
