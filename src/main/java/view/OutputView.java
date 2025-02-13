package view;

import domain.Lotto;
import domain.Ticket;
import java.util.List;

public class OutputView {

    private static final String PURCHASE_RESULT = "%s개를 구매헀습니다.";

    private OutputView(){}

    public static OutputView create() {
        return new OutputView();
    }

    public void printPurchaseResult(Ticket ticket) {
        System.out.printf(PURCHASE_RESULT, ticket.getQuantity());
        changeLine();
    }

    public void printLottos(List<Lotto> lottos){
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    private void changeLine() {
        System.out.println();
    }


}
