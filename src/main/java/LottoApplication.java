import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoApplication {
    public static void main(String[] args) {
        int purchaseAmount = readPurchaseAmount();
        int count = purchaseAmount / 1000;
        System.out.println(count + "개를 구매했습니다.");

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }

        for(Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }

        readWinningNumbers();

    }

    private static void readWinningNumbers() {
        Scanner sc = new Scanner(System.in);

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        while(true) {
            try {
                String inputWinningNumbers = sc.nextLine();
                WinningNumber winningNumber = new WinningNumber(inputWinningNumbers);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

     }


    private static int readPurchaseAmount() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("구입금액을 입력해 주세요.");
                int purchaseAmount = sc.nextInt();
                if (purchaseAmount < 1000 || purchaseAmount > 100000 || purchaseAmount % 1000 != 0) {
                    throw new IllegalArgumentException("1000 이상 100000 이하의 1000으로 나누어 떨어지는 정수를 입력해주세요.");
                }
                return purchaseAmount;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
