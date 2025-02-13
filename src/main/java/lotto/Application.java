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
        WinningNumbers winningNumbers = getWinningNumbers();
        int bonusNumber = getBonusNumber(winningNumbers);
        WinningStatistics winningStatistics = LottoManager.calculateStatistics(lottos, winningNumbers, bonusNumber);
        double returnRate = winningStatistics.calculateReturnRate(lottos.size() * LottoManager.LOTTO_UNIT_PRICE);
    }

    private static List<Lotto> purchaseLottos() {
        try {
            int purchaseAmount = getPurchaseAmount();
            return LottoManager.purchase(purchaseAmount);
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

    private static WinningNumbers getWinningNumbers() {
        try {
            System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] inputWinningNumbers = input.split(",");
            List<Integer> winningNumbers = new ArrayList<>();
            for (String inputWinningNumber : inputWinningNumbers) {
                winningNumbers.add(Integer.parseInt(inputWinningNumber.trim()));
            }
            validateWinningNumbers(winningNumbers);
            return new WinningNumbers(winningNumbers);
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

    private static int getBonusNumber(final WinningNumbers winningNumbers) {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            Scanner scanner = new Scanner(System.in);
            int bonusNumber = scanner.nextInt();
            validateLottoNumber(bonusNumber);
            winningNumbers.validateBonusNumberDuplicated(bonusNumber);
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumber(winningNumbers);
        }
    }

    private static void validateLottoNumber(int lottoNumber) {
        if (lottoNumber < 1 || lottoNumber > 45) {
            throw new IllegalArgumentException("로또 번호는 1 ~ 45 사이여야 합니다.");
        }
    }
}
