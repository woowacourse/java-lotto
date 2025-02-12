package view;

import java.util.Scanner;

public class InputView {
    private final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";

    public String inputLottoMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println(INPUT_MONEY_MESSAGE);
        return sc.nextLine();
    }

    public String inputWinningNumbers(){
        Scanner sc = new Scanner(System.in);
        System.out.println(inputWinningNumbers());
        return sc.nextLine();
    }

}
