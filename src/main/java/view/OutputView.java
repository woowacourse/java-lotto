package view;

import dto.LottoNumbersResponse;
import dto.LottosResponse;
import dto.TicketAmountResponse;

public class OutputView {

    public void printTicketPurchaseAmount(TicketAmountResponse response) {
        System.out.println(response.amount() + "개를 구매했습니다.");
    }

    public void printLottos(LottosResponse response) {
        response.lottos().forEach(this::printLottoNumbers);
    }

    private void printLottoNumbers(LottoNumbersResponse response) {
        String joined = String.join(", ", response.numbers());
        System.out.println("[" + joined + "]");
    }

    public void printWinningNumbersMessage() {

    }
}
