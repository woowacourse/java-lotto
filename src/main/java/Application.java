import view.InputView;
import view.LottoGameScreen;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        GameManageApplication gameManageApplication = new GameManageApplication(new LottoGameScreen(), new InputView(new Scanner(System.in)));
        gameManageApplication.run();
    }
}
