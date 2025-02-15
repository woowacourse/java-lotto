import controller.LottoController;
import exception.LottoException;
import repository.BonusNumberRepository;
import repository.LottoRepository;
import repository.WinningNumberRepository;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoService(new LottoRepository(), new WinningNumberRepository(), new BonusNumberRepository()));
        lottoController.startLotto();
    }

}
