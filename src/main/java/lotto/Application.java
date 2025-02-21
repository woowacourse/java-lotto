package lotto;

import lotto.controller.Controller;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        try (InputView inputView = new InputView()) {
            Controller controller = new Controller(inputView, outputView);
            controller.run();
        } catch (Exception e) {
            outputView.print(e.getMessage());
        }
    }
}
