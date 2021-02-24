import domain.LottoMachine;
import service.LottoService;
import view.InputView;
import view.LottoGameScreen;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        LottoService lottoService = new LottoService(lottoMachine);
        GameManageApplication gameManageApplication = new GameManageApplication(new LottoGameScreen(), lottoService, new InputView(new Scanner(System.in)));
        gameManageApplication.run();
    }
}
