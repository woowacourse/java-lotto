package lotto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        List<Lotto> lottos = purchaseLottos();
        System.out.println("%d개를 구매했습니다.".formatted(lottos.size()));
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }

        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        List<Integer> winningNumbers = getWinningNumbers();

        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = getBonusNumber();
    }

    private static List<Lotto> purchaseLottos() {
        try {
            int purchaseAmount = getPurchaseAmount();
            return new LottoManager().purchase(purchaseAmount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return purchaseLottos();
        }
    }

    private static int getPurchaseAmount() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("구입금액은 숫자여야 합니다.");
            return getPurchaseAmount();
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

    private static int getBonusNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
