package lotto.view;

import lotto.domain.Lottos;
import lotto.messageConstants.MessageConstants;

public class OutputView {

    public void printLottos(Lottos lottos) {
        System.out.println(lottos.getSize() + MessageConstants.BUYLOTTONUMBER);
        getSize(lottos);
        System.out.println();
    }

    private void getSize(Lottos lottos) {
        for (int i = 0; i < lottos.getSize(); i++) {
            System.out.println(lottos.getIndexByLotto(i).getLotto());
        }
    }
}
