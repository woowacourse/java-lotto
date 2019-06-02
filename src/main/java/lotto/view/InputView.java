package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DEMAND_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String NOT_INTERGER_MESSAGE = "정수를 입력해 주세요.";

    public static int getInsertedMoney(){
        System.out.println(DEMAND_MONEY_MESSAGE);
        return getInterger();
    }

    private static int getInterger() {
        try{
            return Integer.parseInt(SCANNER.nextLine());
        } catch (Exception e) {
            System.out.println(NOT_INTERGER_MESSAGE);
            return getInsertedMoney();
        }
    }

}
