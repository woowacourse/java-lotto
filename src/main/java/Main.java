import controller.LottoController;
import service.IssueLottoService;
import service.OpenLottoService;

public class Main {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new IssueLottoService(),new OpenLottoService());
        lottoController.start();
    }

}
