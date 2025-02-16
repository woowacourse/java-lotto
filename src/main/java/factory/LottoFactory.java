package factory;

import generator.RandomNumberGenerator;
import generator.RandomUniqueNumberGenerator;
import model.Lotto;
import view.input.BasicInputParser;
import view.input.ConsoleInputView;
import view.input.InputParser;
import view.input.InputView;
import view.output.ConsoleOutputView;
import view.output.OutputView;

public abstract class LottoFactory {

    public static InputParser inputParser() {
        return new BasicInputParser();
    }

    public static InputView inputView() {
        return new ConsoleInputView(inputParser());
    }

    public static OutputView outputView() {
        return new ConsoleOutputView();
    }

    public static RandomNumberGenerator numberGenerator() {
        return new RandomUniqueNumberGenerator(Lotto.LOTTO_NUMBER_START_INCLUSIVE, Lotto.LOTTO_NUMBER_END_INCLUSIVE);
    }
}
