package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoGenerator;
import lotto.domain.RandomNumberGenerator;

public class Main {

    public static void main(String[] args) {
        LottoGenerator lottoGenerator = new LottoGenerator(new RandomNumberGenerator());
        LottoController controller = new LottoController(lottoGenerator);
        controller.run();
    }
}
