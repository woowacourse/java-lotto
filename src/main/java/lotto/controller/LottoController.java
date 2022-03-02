package lotto.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.LottoCount;
import lotto.model.LottoMachine;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.WinningLotto;
import lotto.model.generator.LottoGenerator;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import lotto.view.InputView;
import lotto.view.ResultView;

public class LottoController {

    private static final String NOT_NUMBER_ERROR_MESSAGE = "[ERROR] 문자가 입력되었습니다.";

    private static final String NUMBER_REGEX = "\\d+";

    private LottoGenerator lottoGenerator;

    public LottoController(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        Money money = validateMoney(InputView.inputMoney());
        LottoCount lottoCount = new LottoCount(validateNumber(InputView.inputManualLottoCount()), money);
        Lottos manualLottos = inputManualLottos(lottoCount.getManualLottoCount());
        LottoMachine lottoMachine = new LottoMachine(lottoGenerator, money, lottoCount, manualLottos);
        ResultView.printBuyingLottosResult(lottoCount, lottoMachine.getLottos());
        WinningLotto winningLotto = makeWinningLotto(InputView.inputWinningNumbers(),
                InputView.inputBonusNumber());
        lottoMachine.calculateResult(winningLotto);
        ResultView.printTotalRankResult(lottoMachine);
    }

    private Money validateMoney(String money) {
        if (!Pattern.matches(NUMBER_REGEX, money)) {
            throw new IllegalArgumentException(Money.MONEY_ERROR_MESSAGE);
        }
        return new Money(Integer.parseInt(money));
    }

    private int validateNumber(String number) {
        try {
            String trimNumber = number.trim();
            return Integer.parseInt(trimNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR_MESSAGE);
        }
    }

    private Lottos inputManualLottos(int count) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            manualLottos.add(makeLotto(InputView.inputManualLottoNumbers()));
        }
        return new Lottos(manualLottos);
    }

    private Lotto makeLotto(String numbers) {
        LottoNumbers lottoNumbers = splitNumbers(numbers);
        return new Lotto(lottoNumbers);
    }

    private WinningLotto makeWinningLotto(String winningNumbers, String bonusNumber) {
        LottoNumbers lottoNumbers = splitNumbers(winningNumbers);
        LottoNumber lottoBonusNumber = new LottoNumber(toIntBonusNumber(bonusNumber));
        return new WinningLotto(lottoNumbers, lottoBonusNumber);
    }

    private LottoNumbers splitNumbers(String numbers) {
        String[] splitNumbers = validateLottoNumbers(numbers);
        return new LottoNumbers(Arrays.stream(splitNumbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private String[] validateLottoNumbers(String numbers) {
        String[] splitNumbers = numbers.split(",");
        for (String number : splitNumbers) {
            validateNumber(number);
        }
        return trimLottoNumbers(splitNumbers);
    }

    private String[] trimLottoNumbers(String[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i].trim();
        }
        return numbers;
    }

    private int toIntBonusNumber(String bonusNumber) {
        return validateNumber(bonusNumber);
    }
}
