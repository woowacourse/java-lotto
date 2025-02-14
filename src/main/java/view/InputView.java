package view;

import static domain.LottoTicket.LOTTO_PRICE;

import java.util.Arrays;
import java.util.List;
import util.Console;

public class InputView {
    public static int inputPurchaseAmount() {
        System.out.println("구매금액을 입력해 주세요.");
        String purchaseAmount = Console.readLine();
        validatePurchaseAmount(purchaseAmount);
        return Integer.parseInt(purchaseAmount);
    }

    private static void validatePurchaseAmount(String purchaseAmount) {
        validateInteger(purchaseAmount);
        int amount = Integer.parseInt(purchaseAmount);
        validatePositiveInteger(amount);
        validateLottoPriceUnit(amount);
    }

    private static void validateInteger(String purchaseAmount) {
        try {
            Integer.parseInt(purchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수를 입력해주세요.");
        }
    }

    private static void validateLottoPriceUnit(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("로또 금액(1000)으로 나누어 떨어지지 않습니다.");
        }
    }

    private static void validatePositiveInteger(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("양수가 아닙니다.");
        }
    }

    public static List<Integer> inputWinningLottoTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningLottoTicket = Console.readLine();
        validateLottoTicket(winningLottoTicket);
        return Arrays.stream(winningLottoTicket.split(",", -1))
                .map(String::strip)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    private static void validateLottoTicket(String winningLottoTicket) {
        List<String> winningLottoTickets = Arrays.stream(winningLottoTicket
                .split(",", -1)).toList();
        List<Integer> numbers = winningLottoTickets.stream()
                .map(String::strip)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }

    public static int inputBonusNumber(List<Integer> winningNumbers) {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = Integer.parseInt(Console.readLine());
        validateBonusNumber(bonusNumber, winningNumbers);
        return bonusNumber;
    }

    private static void validateBonusNumber(Integer bonusNumber, List<Integer> winningNumbers) {
        validateDuplicateNumber(bonusNumber, winningNumbers);
    }

    private static void validateDuplicateNumber(Integer bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }
}
