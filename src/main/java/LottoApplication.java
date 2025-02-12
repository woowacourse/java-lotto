import java.util.Scanner;

public class LottoApplication {
    public static void main(String[] args) {

        int purchaseAmount = readPurchaseAmount();
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
