package lotto;

import java.util.List;
import java.util.Set;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        int purchaseAmount = inputView.requestPurchaseAmount();
        Cashier cashier = new Cashier();
        List<Lotto> lottos = cashier.payForLotto(purchaseAmount);
        outputView.printLottos(convertLottoDtos(lottos));
        Lotto winningLotto = new Lotto(Set.copyOf(inputView.requestWinningNumbers()));
    }

    private List<LottoDto> convertLottoDtos(List<Lotto> lottos) {
        return lottos.stream().map(LottoDto::of).toList();
    }
}
