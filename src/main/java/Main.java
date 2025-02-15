import controller.LottoController;
import service.LottoService;

public class Main {

    public static void main(String[] args) {

        LottoService lottoService = new LottoService();
        LottoController lottoController = new LottoController(lottoService);
        lottoController.run();
    }
}
