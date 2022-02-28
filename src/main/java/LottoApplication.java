import controller.LottoController;
import domain.strategy.LottoNumberGenerateStrategy;
import domain.strategy.RandomLottoNumberGenerator;

public class LottoApplication {
    public static void main(String[] args) {
        LottoNumberGenerateStrategy lottoNumberGenerator = new RandomLottoNumberGenerator();
        LottoController lottoController = new LottoController(lottoNumberGenerator);
        lottoController.start();
    }
}
