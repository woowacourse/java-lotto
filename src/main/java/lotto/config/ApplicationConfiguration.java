package lotto.config;

import java.util.Scanner;
import lotto.domain.LottoMachine;
import lotto.service.InputService;
import lotto.service.OutputService;
import lotto.utility.RandomGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ApplicationConfiguration {

    private final Scanner scanner = new Scanner(System.in);
    private final InputView inputView = new InputView(scanner);
    private final OutputView outputView = new OutputView();
    private final InputService inputService = new InputService(inputView, outputView);
    private final OutputService outputService = new OutputService(outputView);
    private final RandomGenerator randomGenerator = new RandomGenerator();
    private final LottoMachine lottoMachine = new LottoMachine(randomGenerator);

    public InputService getInputService() {
        return inputService;
    }

    public OutputService getOutputService() {
        return outputService;
    }

    public LottoMachine getLottoMachine() {
        return lottoMachine;
    }
}
