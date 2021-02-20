import controller.LottoSystemController;
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
        LottoSystemController lottoSystemController = new LottoSystemController();
        lottoSystemController.run();
    }
}
