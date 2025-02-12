package lotto;

import java.util.ArrayList;
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

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> winningNumbers = getWinningNumbers();
    }

    private static int getPurchaseAmount() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("구입금액은 숫자여야 합니다.");
        }
    }

    private static List<Integer> getWinningNumbers() {
        Scanner scanner = new Scanner(System.in);
        String[] inputWinningNumbers = scanner.next().split(",");
        List<Integer> winningNumbers = new ArrayList<>();
        for (String inputWinningNumber : inputWinningNumbers) {
            winningNumbers.add(Integer.parseInt(inputWinningNumber));
        }
        return winningNumbers;
    }
}
