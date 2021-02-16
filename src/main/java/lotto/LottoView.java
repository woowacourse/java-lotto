package lotto;

import java.util.Scanner;

public class LottoView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String requestMoney() {
        System.out.println("구입금액을 입력해주세요");
        return SCANNER.nextLine();
    }

    public static void buyLotto(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }
}
