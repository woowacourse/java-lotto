import controller.LottoController;
import domain.strategy.LottoNumberGenerateStrategy;
import domain.strategy.RandomLottoNumberGenerateStrategy;

public class LottoApplication {
    public static void main(String[] args) {
        LottoNumberGenerateStrategy lottoNumberGenerator = new RandomLottoNumberGenerateStrategy();
        LottoController lottoController = new LottoController(lottoNumberGenerator);
        lottoController.start();
    }
}
