import controller.LottoController;
import domain.strategy.RandomLottoNumberGenerateStrategy;

public class LottoApplication {
    public static void main(String[] args) {
        new LottoController(new RandomLottoNumberGenerateStrategy()).start();
    }
}
