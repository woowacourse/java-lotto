import domain.LottoShop;
import java.io.IOException;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) throws IOException {
        LottoController lottoController = new LottoController(new InputView(), new OutputView(), new LottoShop());
        lottoController.run();
    }
}
