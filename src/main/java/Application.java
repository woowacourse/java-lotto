import controller.LottoController;
import repository.BonusNumberRepository;
import repository.LottoRepository;
import repository.WinningNumberRepository;
import service.LottoService;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoService(new LottoRepository(), new WinningNumberRepository(), new BonusNumberRepository()));
        lottoController.startLotto();
    }

}
