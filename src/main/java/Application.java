import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        AppConfig appConfig = new AppConfig();
        LottoController lottoController = appConfig.controller();
        lottoController.run();
    }
}
