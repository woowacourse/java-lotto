import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        LottoController lottoController = new LottoController(new InputView(), new OutputView(), new LottoManager());
        lottoController.run();
    }
}
