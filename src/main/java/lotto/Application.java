package lotto;

import lotto.game.LottoController;
import lotto.game.LottoService;
import lotto.ticket.strategy.NumbersGenerator;
import lotto.ticket.strategy.RandomNumbersGenerator;

public class Application {
    public static void main(String[] args) {
        NumbersGenerator autoTicketGenerator = new RandomNumbersGenerator();
        LottoService lottoService = new LottoService(autoTicketGenerator);
        LottoController lottoController = new LottoController(lottoService);
        lottoController.run();
    }
}
