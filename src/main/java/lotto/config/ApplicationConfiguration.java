package lotto.config;

import java.util.Scanner;
import lotto.domain.LottoMachine;
import lotto.utility.RandomGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.validator.InputValidator;

public class ApplicationConfiguration {

    private final Scanner scanner = new Scanner(System.in);
    private final InputValidator inputValidator = new InputValidator();
    private final InputView inputView = new InputView(scanner, inputValidator);
    private final OutputView outputView = new OutputView();
    private final RandomGenerator randomGenerator = new RandomGenerator();
    private final LottoMachine lottoMachine = new LottoMachine(randomGenerator);

    public InputView getInputView() {
        return inputView;
    }

    public OutputView getOutputView() {
        return outputView;
    }

    public LottoMachine getLottoMachine() {
        return lottoMachine;
    }
}
