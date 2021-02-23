import domain.LottoGameMachine;
import service.LottoService;
import view.LottoGameScreen;

public class Application {
    public static void main(String[] args) {
        LottoGameMachine lottoGameMachine = new LottoGameMachine();
        LottoService lottoService = new LottoService(lottoGameMachine);
        GameManageApplication gameManageApplication = new GameManageApplication(new LottoGameScreen(), lottoService);
        gameManageApplication.run();
    }
}
