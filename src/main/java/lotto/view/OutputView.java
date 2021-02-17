package lotto.view;

public class OutputView {

    private static final String ASK_HOW_MUCH_TO_BUY = "구입금액을 입력해 주세요.";
    private static final String HOW_MUCH_YOU_BOUGHT = "%d개를 구매했습니다.";
    private static final String ASK_WINNER_LOTTO_TICKET = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ASK_WINNER_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String REWARD_RESULT_BOARD = "당첨 통계";
    private static final String NEWLINE = System.lineSeparator();
    private static final String RESULT_LINE = "---------";
    private static final String EACH_RESULT = "%d개 일치 (%d원)- %d개";
    private static final String FINAL_RESULT = "총 수익률은 %d%입니다.";

    public static void askHowMuchToBuy(){
        System.out.println(ASK_HOW_MUCH_TO_BUY);
    }

    public static void howMuchYouBought(int count){
        System.out.printf(HOW_MUCH_YOU_BOUGHT, count);
    }
}
