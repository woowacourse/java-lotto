package lotto;

import java.util.List;
import java.util.Set;
import lotto.controller.LottoController;
import lotto.model.Cashier;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.LottoTicket;
import lotto.model.TestNumberPicker;
import lotto.model.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoFixtures {

    public static Cashier testCashier = new Cashier(new LottoMachine(new TestNumberPicker()));
    public static Lotto lottoOneToSix = new Lotto(Set.of(1, 2, 3, 4, 5, 6));
    public static LottoTicket lottoTicketOneToSix = new LottoTicket(List.of(lottoOneToSix));
    public static WinningLotto winningLottoOneToSix = new WinningLotto(lottoOneToSix, 7);

    public static Lotto createByNumbers(Set<Integer> numbers) {
        return new Lotto(numbers);
    }

    public static LottoController createLottoController(Cashier cashier) {
        return new LottoController(new InputView(), new OutputView(), cashier);
    }
}
