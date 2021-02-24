import domain.RandomLottoMachine;
import service.LottoService;
import view.LottoGameScreen;

public class Application {
    public static void main(String[] args) {
        RandomLottoMachine randomLottoMachine = new RandomLottoMachine();
        LottoService lottoService = new LottoService(randomLottoMachine);
        GameManageApplication gameManageApplication = new GameManageApplication(new LottoGameScreen(), lottoService);
        gameManageApplication.run();
    }
}
