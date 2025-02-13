package view;

import domain.Ticket;

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

    private void changeLine() {
        System.out.println();
    }


}
