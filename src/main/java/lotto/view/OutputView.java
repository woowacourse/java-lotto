package lotto.view;

public class OutputView {
    private static final String NEW_LINE = System.getProperty("line.separator");

    public static void printErrorMsg(Exception e) {
        System.out.println(e.getMessage() + NEW_LINE);
    }
}
