package src.view.output;

import java.util.List;

public class ConsoleOutputView implements OutputView {

    @Override
    public void printInputPurchaseMoneyMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    @Override
    public void printPurchasedLottos(List<LottoResponse> responses) {
        System.out.printf("%d개를 구매했습니다.\n", responses.size());
        responses.forEach(this::printLotto);
    }

    private void printLotto(LottoResponse response) {
        System.out.println(response.getNumbers().stream().sorted().toList());
    }
}
