public class OutputView {

    private static final String COUNT_MESSAGE = "%d개를 구매했습니다.\n";

    public static void printCountOfLotto(int count) {
        System.out.printf(COUNT_MESSAGE, count);
    }
}