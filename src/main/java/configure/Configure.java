package configure;

import controller.LottoController;
import util.RandomGenerator;
import util.RandomGeneratorImpl;

public class Configure {
    private static LottoController lottoController;
    private static RandomGenerator randomGenerator;

    public LottoController lottoController() {
        if (lottoController == null) {
            return new LottoController(randomGenerator());
        }
        return lottoController;
    }

    public RandomGenerator randomGenerator() {
        if (randomGenerator == null) {
            return new RandomGeneratorImpl();
        }
        return randomGenerator;
    }
}
