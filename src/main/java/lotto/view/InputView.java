package lotto.view;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static String promptPrice(){
        System.out.println("구입 금액을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static List<String> promptWinningNumber() {
        System.out.println("지난 주 당첨번호를 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        return Arrays.asList(scanner.nextLine().split(","));
    }

    public static String promptBonusBall() {
        System.out.println("보너스 볼을 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
