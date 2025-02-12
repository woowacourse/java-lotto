import controller.LottoController;
import view.Output;

public class Application {
    public static void main(String[] args) {
        Output output = new Output();

        LottoController lottoController = new LottoController(output);
        lottoController.run();
    }
}
