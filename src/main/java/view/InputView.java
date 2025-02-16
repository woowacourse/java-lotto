package view;

import domain.LottoTicket;
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
    }

    private static void validateInteger(String purchaseAmount) {
        try {
            Integer.parseInt(purchaseAmount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(InputErrorMessage.INTEGER_REQUIRED.getMessage());
        }
    }


    private static void validatePositiveInteger(int purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException(InputErrorMessage.POSITIVE_INTEGER_REQUIRED.getMessage());
        }
    }

    public static List<Integer> inputWinningLottoTicket() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningLottoTicket = Console.readLine();
        validateLottoTicket(winningLottoTicket);

        List<Integer> lottoNumbers = Arrays.stream(winningLottoTicket.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
        LottoTicket.from(lottoNumbers);
        return lottoNumbers;
    }

    private static void validateLottoTicket(String winningLottoTicket) {
        List<String> winningLottoTickets = Arrays.stream(winningLottoTicket.split(",")).toList();
        List<Integer> numbers = winningLottoTickets.stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(InputErrorMessage.DUPLICATED.getMessage());
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
            throw new IllegalArgumentException(InputErrorMessage.DUPLICATED.getMessage());
        }
    }
}
