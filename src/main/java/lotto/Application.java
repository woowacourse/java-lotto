package lotto;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();
        List<Set<Integer>> lottos = new LottoManager().purchase(amount);
    }
}
