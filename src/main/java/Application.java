import view.LottoGameScreen;
import view.MainScreen;

public class Application {
    public static void main(String[] args) {
        GameManageApplication gameManageApplication = new GameManageApplication(new MainScreen(), new LottoGameScreen());
        gameManageApplication.run();
    }
}
