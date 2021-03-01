import lotto.controller.LottoController;
import lotto.domain.LottoTicketsGenerator;
import lotto.domain.RandomLottoTicketsGenerator;

public class Application {
    public static void main(String[] args) {
        final LottoTicketsGenerator lottoTicketsGenerator = new RandomLottoTicketsGenerator();
        final LottoController lottoController = new LottoController(lottoTicketsGenerator);
        lottoController.start();
    }
}
