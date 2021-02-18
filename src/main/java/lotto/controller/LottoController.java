package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.view.OutputView;
import lotto.view.Screen;

public class LottoController {

    private static final String REGEX = ", ";

    private final LottoTickets lottoTickets;
    private final WinningLotto winningLotto;
    private final int buyMoney;

    public LottoController(String value) {
        this.buyMoney = Integer.parseInt(value);
        lottoTickets = new LottoTickets(this.buyMoney / 1000);
        OutputView.printBuyLottoCountMessage(this.buyMoney / 1000);
        showLottoTickets();
        this.winningLotto = createWinningLotto();
    }

    public void run() {
        OutputView.printResultMessage(lottoTickets.getResult(winningLotto), buyMoney);
    }

    private WinningLotto createWinningLotto() {
        String values = Screen.getLottoNumbers();
        List<Integer> numbers = Arrays.asList(values.split(REGEX)).stream()
            .mapToInt(Integer::parseInt)
            .boxed()
            .collect(Collectors.toList());
        int bonusNumber = Integer.parseInt(Screen.getBonusBallNumber());
        return new WinningLotto(numbers, bonusNumber);
    }

    private void showLottoTickets() {
        for (Lotto lotto : lottoTickets.getLottoTickets()) {
            OutputView.printLottoMessage(lotto.getLottoNumbers());
        }
        OutputView.printNewLineMessage();
    }
}
