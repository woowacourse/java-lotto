import controller.LottoController;
import generator.NumberGenerator;
import generator.RandomUniqueNumberGenerator;
import model.Lotto;
import view.input.BasicInputParser;
import view.input.ConsoleInputView;
import view.input.InputParser;
import view.input.InputView;
import view.output.ConsoleOutputView;
import view.output.OutputView;

public class LottoApplication {

    public static void main(final String[] args) {
        final InputParser inputParser = new BasicInputParser();
        final InputView inputView = new ConsoleInputView(inputParser);
        final OutputView outputView = new ConsoleOutputView();
        final NumberGenerator numberGenerator =
            new RandomUniqueNumberGenerator(Lotto.LOTTO_NUMBER_START_INCLUSIVE, Lotto.LOTTO_NUMBER_END_INCLUSIVE);

        final LottoController controller =
            new LottoController(inputView, outputView, numberGenerator);
        controller.run();
    }
}
