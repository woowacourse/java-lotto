package lotto.view;

public class OutputView {

    private static final String LINE = System.lineSeparator();

    public void printLottoCount(final int lottoCount) {
        System.out.printf("%d개를 구매했습니다." + LINE, lottoCount);
    }
}
