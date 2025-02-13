package view;

import domain.LottoFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



public class InputView {
    static Scanner sc = new Scanner(System.in);

    public static int inputPurchaseAmount(){
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = Integer.parseInt(sc.nextLine());
        System.out.println(purchaseAmount / LottoFactory.LOTTO_PRICE + "개를 구매했습니다.");
        return purchaseAmount;
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> winningNumbers = Arrays.stream(sc.nextLine()
                        .split(","))
                .map(String::trim)
                        .map(Integer::parseInt)
                        .toList();
        return winningNumbers;
    }

    public static int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bounusBall = sc.nextInt();
        return bounusBall;
    }
}
