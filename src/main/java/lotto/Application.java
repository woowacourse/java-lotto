package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = getPurchaseAmount();
        List<Lotto> lottos = new LottoManager().purchase(purchaseAmount);
        System.out.println("%d개를 구매했습니다.".formatted(lottos.size()));
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    private static int getPurchaseAmount() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("구입금액은 숫자여야 합니다.");
        }
    }
}
