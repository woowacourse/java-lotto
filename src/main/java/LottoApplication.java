import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoApplication {
    private static Scanner sc = new Scanner(System.in);

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

        WinningNumber winningNumber = readWinningNumbers();
        BonusNumber bonusNumber = readBonusNumbers(winningNumber);

        WinningResult winningResult = new WinningResult();
        for (Lotto lotto : lottos) {
            // 당첨 번호와의 일치 개수 구하기
            int matchingCount = winningNumber.findMatchingCountWith(lotto.getNumbers());
            boolean matchesBonusNumber = bonusNumber.matchesWith(lotto.getNumbers());
            WinningStatus winningStatus = WinningStatus.findBy(matchingCount, matchesBonusNumber);
            winningResult.update(winningStatus);
        }

        for (WinningStatus winningStatus : winningResult.getWinningResults().keySet()) {
            int winningCount = winningResult.getWinningResults().get(winningStatus);
            System.out.printf("%s - %d개\n", winningStatus.getExpression(), winningCount);
        }
    }

    private static BonusNumber readBonusNumbers(WinningNumber winningNumber) {
        System.out.println("보너스 볼을 입력해 주세요.");
        while (true) {
            //TODO : BonusNumber 객체로 검증 로직 분리하기
            try {
                int bonusNumber = sc.nextInt();
                if (bonusNumber < 1 || bonusNumber > 45) {
                    throw new IllegalArgumentException("보너스 볼은 1~45 사이의 정수로 입력해주세요.");
                }
                if (winningNumber.contains(bonusNumber)) {
                    throw new IllegalArgumentException("보너스 볼은 당첨 번호와 중복되지 않게 입력해주세요.");
                }
                return new BonusNumber(bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static WinningNumber readWinningNumbers() {
        sc.nextLine();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        while(true) {
            try {
                String inputWinningNumbers = sc.nextLine();
                return new WinningNumber(inputWinningNumbers);
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
     }


    private static int readPurchaseAmount() {
        while (true) {
            try {
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
