package lotto.view;

public class OutputView {
    public static final String COMMA = ", ";

    private OutputView() { /* prevent creating OutputView instance */ }

    public static void printLottoCount(int lottoCount) {
        System.out.println(String.format("%d개를 구매했습니다.", lottoCount));
    }
}
