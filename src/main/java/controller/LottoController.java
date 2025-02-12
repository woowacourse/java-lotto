package controller;

import domain.Lottos;
import domain.LottosFactory;
import java.util.Scanner;

public class LottoController {
    public static final int LOTTO_PRICE = 1000;

    public void run() {
        String price = inputPrice();
        int amount = getAmount(price);
        System.out.println(amount + "개를 구매했습니다.");

        LottosFactory lottosFactory = new LottosFactory();
        Lottos lottos = lottosFactory.from(amount);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

    }

    public static String inputPrice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        return sc.nextLine();
    }

    // todo : 예외처리
    public static int getAmount(String inputPrice) {
        return Integer.parseInt(inputPrice) / LOTTO_PRICE;
    }
}
