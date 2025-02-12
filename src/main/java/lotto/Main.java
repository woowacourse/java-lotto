package lotto;

public class Main {

    public static void main(String[] args) {
        LottoController controller = initController();
        controller.start();
    }

    private static LottoController initController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        return new LottoController(inputView, outputView);
    }
}
