package lotto;

import java.util.ArrayList;
import java.util.HashSet;
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
        try {
            System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
            Scanner scanner = new Scanner(System.in);
            String[] inputWinningNumbers = scanner.next().split(",");
            List<Integer> winningNumbers = new ArrayList<>();
            for (String inputWinningNumber : inputWinningNumbers) {
                winningNumbers.add(Integer.parseInt(inputWinningNumber));
            }
            validateWinningNumbers(winningNumbers);
            return winningNumbers;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningNumbers();
        }
    }

    private static void validateWinningNumbers(List<Integer> winningNumbers) {
        if (new HashSet<>(winningNumbers).size() != 6) {
            throw new IllegalArgumentException("6개의 고유한 번호를 입력해야 합니다.");
        }

        for (int winningNumber : winningNumbers) {
            validateLottoNumber(winningNumber);
        }
    }

    private static void validateLottoNumber(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이여야 합니다.");
        }
    }

    private static int getBonusNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
