package lotto.view;

import lotto.domain.MyLotto;

public class OutputView {
    private static final String MESSAGE_BUY_LOTTO = "%d개를 구매했습니다.";

    public static void printMyLotto(MyLotto myLotto) {
        System.out.println(String.format(MESSAGE_BUY_LOTTO, myLotto.getSize()));

        for (int i = 0; i < myLotto.getSize(); i++) {
            System.out.println(myLotto.getIndexByLotto(i).toString());
        }
    }
}
