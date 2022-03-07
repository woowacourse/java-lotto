package lotterymachine.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotterymachine.dto.ManualTicketDto;
import lotterymachine.vo.Count;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private static final int TICKET_SIZE = 6;
    private static final String NUMBER_DELIMITER = ",";
    private static final String IS_NOT_NUMBER_EXCEPTION = "숫자만 입력할 수 있습니다.";
    private static final String MANUAL_TICKET_SIZE_EXCEPTION = "6개의 숫자를 입력해야합니다.";
    private static final String MANUAL_TICKET_DUPLICATE_NUMBER_EXCEPTION = "숫자가 중복될 수 없습니다.";
    private static final String DUPLICATE_BONUS_NUMBER_EXCEPTION = "보너스 볼이 당첨 번호와 중복됩니다.";

    public static int getAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return toInt(scanner.nextLine());
    }

    public static int getNumberOfManualTickets() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return toInt(scanner.nextLine());
    }

    public static List<ManualTicketDto> getManualTickets(Count numberOfManualTickets) {
        OutputView.printInputManualPurchase(numberOfManualTickets.isZero());
        try {
            return getManualTicketsDto(numberOfManualTickets);
        } catch (RuntimeException exception) {
            OutputView.printException(exception.getMessage());
            return getManualTickets(numberOfManualTickets);
        }
    }

    private static List<ManualTicketDto> getManualTicketsDto(Count numberOfManualTickets) {
        List<ManualTicketDto> manualTickets = new ArrayList<>();
        for (int i = 0; i < numberOfManualTickets.getNumber(); i++) {
            String[] input = scanner.nextLine().split(NUMBER_DELIMITER);
            List<Integer> manualTicket = toIntegers(input);
            validateManualTicketSize(manualTicket);
            validateDuplication(manualTicket);
            manualTickets.add(new ManualTicketDto(manualTicket));
        }
        return manualTickets;
    }

    private static void validateManualTicketSize(List<Integer> ticket) {
        if (ticket.size() != TICKET_SIZE) {
            throw new IllegalArgumentException(MANUAL_TICKET_SIZE_EXCEPTION);
        }
    }

    private static void validateDuplication(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(MANUAL_TICKET_DUPLICATE_NUMBER_EXCEPTION);
        }
    }

    public static List<Integer> getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] input = scanner.nextLine().split(NUMBER_DELIMITER);
        return toIntegers(input);
    }

    public static int getBonusNumber(List<Integer> numbers) {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            int bonusNumber = toInt(scanner.nextLine());
            validateBonusNumber(numbers, bonusNumber);
            return bonusNumber;
        } catch (RuntimeException runtimeException) {
            OutputView.printException(runtimeException.getMessage());
            return getBonusNumber(numbers);
        }
    }

    private static void validateBonusNumber(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATE_BONUS_NUMBER_EXCEPTION);
        }
    }

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (RuntimeException runtimeException) {
            throw new IllegalArgumentException(IS_NOT_NUMBER_EXCEPTION);
        }
    }

    private static List<Integer> toIntegers(String[] input) {
        return Arrays.stream(input)
                .map(String::trim)
                .map(InputView::toInt)
                .collect(Collectors.toList());
    }
}
