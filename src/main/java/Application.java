import controller.LottoController;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        try {
            start();
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            run();
        }
    }

    private static void start() {
        LottoController lottoController = new LottoController();
        lottoController.run();
    }
}
