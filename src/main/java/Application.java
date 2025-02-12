import controller.LottoController;
import repository.LottoRepository;
import service.LottoService;
import view.InputView;

public class Application {

    private static final LottoController lottoController = new LottoController(new LottoService(new LottoRepository()));
    public static void main(String[] args) {
        buyLotto();

    }

    private static void buyLotto() {
        String inputBuyLottoMoney = inputBuyLottoMoney();
        lottoController.inputBuyLottoMoney(inputBuyLottoMoney);


    }

    private static String inputBuyLottoMoney() {
        return InputView.inputBuyLottoMoney();
    }
}
