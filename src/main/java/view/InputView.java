package view;

import static domain.LottoTicket.LOTTO_PRICE;

import java.util.Arrays;
import java.util.List;
import util.Console;
import util.RetryHandler;

public class InputView {
    public static int inputPurchaseAmount() {
        return RetryHandler.retryOnInvalidInput(() -> {
            System.out.println("구매금액을 입력해 주세요.");
            String purchaseAmount = Console.readLine();
            validatePurchaseAmount(purchaseAmount);
            return Integer.parseInt(purchaseAmount);
        });
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
        return RetryHandler.retryOnInvalidInput(() -> {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            String winningLottoTicket = Console.readLine();
            validateLottoTicket(winningLottoTicket);
            return Arrays.stream(winningLottoTicket.split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
        });
    }

    private static void validateLottoTicket(String winningLottoTicket) {
        List<String> winningLottoTickets = Arrays.stream(winningLottoTicket.split(",")).toList();
        validateLottoSize(winningLottoTickets);
        List<Integer> numbers = winningLottoTickets.stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
        numbers.forEach(InputView::validateLottoNumberRange);
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }

    private static void validateLottoSize(List<String> winningLottoTickets) {
        if (winningLottoTickets.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
    }

    private static void validateLottoNumberRange(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("로또 번호는 1 이상 45 이하이다.");
        }
    }

    public static int inputBonusNumber(List<Integer> winningNumbers) {
        return RetryHandler.retryOnInvalidInput(() -> {
            System.out.println("보너스 볼을 입력해 주세요.");
            int bonusNumber = Integer.parseInt(Console.readLine());
            validateBonusNumber(bonusNumber, winningNumbers);
            return bonusNumber;
        });
    }

    private static void validateBonusNumber(Integer bonusNumber, List<Integer> winningNumbers) {
        validateLottoNumberRange(bonusNumber);
        validateDuplicateNumber(bonusNumber, winningNumbers);
    }

    private static void validateDuplicateNumber(Integer bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 번호가 존재합니다.");
        }
    }
}
