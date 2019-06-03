package lotto.view;

import java.util.Scanner;

import static lotto.view.OutPutView.ENTER;

public class InputView {
    private static final String SPLIT_REGEX = ",";
    private static Scanner scanner = new Scanner(System.in);

    public static String InputPrice() {
        System.out.println("구매금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String InputNumberOfCustomLotto(){
        System.out.println(ENTER + "수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String[] InputCustomLottoNumber(){
        System.out.println(ENTER + "수동으로 구매할 번호를 입력해 주세요.");
        return scanner.nextLine().split(SPLIT_REGEX);
    }

    public static String[] InputWinLottoNumber() {
        System.out.println(ENTER + "지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine().split(SPLIT_REGEX);
    }

    public static String InputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}
