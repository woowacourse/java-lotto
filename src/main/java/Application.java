import domain.LottoMachine;
import service.LottoService;
import view.LottoGameScreen;

public class Application {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        LottoService lottoService = new LottoService(lottoMachine);
        GameManageApplication gameManageApplication = new GameManageApplication(new LottoGameScreen(), lottoService);
        gameManageApplication.run();
    }
}
