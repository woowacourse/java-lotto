package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.WinningLottoNumbers;
import lotto.view.printer.InputPrinter;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NUMERIC_REGULAR_EXPRESSION = "\\d+";
    public static final String LOTTO_NUMBER_DELIMITER = ", ";

    private InputView() {
    }

    public static int getPurchasePrice() {
        InputPrinter.printPurchasePriceInputGuideMessage();
        String purchasePriceInput = scanner.nextLine();
        return parseNumericInput(purchasePriceInput);
    }

    public static int getManualTicketCount() {
        InputPrinter.printManualTicketCountInputGuideMessage();
        String manualTicketNumberInput = scanner.nextLine();
        return parseNumericInput(manualTicketNumberInput);
    }

    public static List<Integer> getLottoNumbers() {
        String lottoNumbersInput = scanner.nextLine();
        validateAllNaturalNumbers(lottoNumbersInput);
        return Arrays.stream(lottoNumbersInput.split(LOTTO_NUMBER_DELIMITER))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private static int parseNumericInput(String inputValue) {
        if (!inputValue.matches(NUMERIC_REGULAR_EXPRESSION)) {
            throw new IllegalArgumentException("자연수를 입력해주세요.");
        }
        return Integer.parseInt(inputValue);
    }

    private static void validateAllNaturalNumbers(String winningNumbersInput) {
        if (!Arrays.stream(winningNumbersInput.split(LOTTO_NUMBER_DELIMITER))
            .allMatch(numberInput -> numberInput.matches(NUMERIC_REGULAR_EXPRESSION))) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다.");
        }
    }

    public static int getBonusNumberInput() {
        InputPrinter.printBonusNumberInputGuideMessage();
        String bonusNumberInput = scanner.nextLine();
        InputPrinter.printNewLine();
        return parseNumericInput(bonusNumberInput);
    }
}
