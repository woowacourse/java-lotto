import controller.LottoController;
import util.random.LottoRandomUtil;
import util.random.RandomUtil;

public class Application {

    public static void main(String[] args) {
        RandomUtil randomUtil = new LottoRandomUtil();

        LottoController lottoController = new LottoController(randomUtil);
        lottoController.run();
    }
}
