package view;

public class OutputView {
    private final String TOTAL_LOTTO_FORMAT = "%d개를 구매했습니다.\n";

    public void displayLottos(int totalLotto, String result) {
        System.out.printf(TOTAL_LOTTO_FORMAT, totalLotto);
        System.out.println(result);
    }
}
