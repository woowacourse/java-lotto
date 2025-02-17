package view;

import static util.constant.Message.*;

import java.util.Scanner;

public class InputView {

    public String inputLottoMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.println(INPUT_MONEY_MESSAGE);
        return sc.nextLine();
    }

    public String inputWinningNumbers() {
        Scanner sc = new Scanner(System.in);
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        return sc.nextLine();
    }

    public String inputBonusNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return sc.nextLine();
    }
}
