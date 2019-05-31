package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.messageConstants.MessageConstants;

import java.util.List;

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

    public void printResult(List<Rank> ranks, double returnRate) {
        System.out.println(MessageConstants.RESULT);
        System.out.println(MessageConstants.BASIC_BAR);

        for (Rank rank : Rank.values()) {
            System.out.println(rank.getMatchString(ranks));
        }

        System.out.printf(MessageConstants.RETURNRATE, returnRate);
    }

}
