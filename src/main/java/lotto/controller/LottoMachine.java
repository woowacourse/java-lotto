package lotto.controller;

import lotto.model.Lottos;
import lotto.view.InputView;

public class LottoMachine {

    private final InputView inputView;

    public LottoMachine(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        String money = inputView.readLine();
        makeLottos(money);
    }

    private void makeLottos(final String money) {
        try {
            Lottos.issue(Integer.parseInt(money));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 금액 형식입니다.");
        }
    }

}
