package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class OutputView {
    public void printAskMoneyInputMessage() {
        System.out.println("구입금액을 입력해주세요.");
    }

    public void printErrorMessage(String e) {
        System.out.println(e);
    }

    public void printMoneyAmount(int money) {
        System.out.println(money);
    }

    public void printPurchasedLotto(Lottos lottos) {
        System.out.println(String.format("%d개를 구매했습니다.", lottos.getLottosSize()));
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.getPickedNumbers());
        }
    }
}
