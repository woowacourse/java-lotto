import configure.Configure;
import controller.LottoController;
import service.IssueLottoService;
import service.OpenLottoService;

public class Main {
    public static void main(String[] args) {
        Configure configure = new Configure();
        configure.lottoController().start();
    }
}
