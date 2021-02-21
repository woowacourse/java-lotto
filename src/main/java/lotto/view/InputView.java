package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.WinningTicketAndBonusNumber;
import lotto.view.printer.InputPrinter;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INTEGER_REGEX = "\\d+";
    public static final String LOTTO_NUMBER_DELIMITER = ", ";

    private InputView() {
    }

    public static UserPurchase getUserPurchase() {
        try {
            return getUserPurchaseInputFromUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getUserPurchase();
        }
    }

    private static UserPurchase getUserPurchaseInputFromUser() {
        int purchasePrice = getPurchasePriceInputFromUser();
        int numberOfManualPurchaseTickets = getNumberOfManualPurchaseTickets();
        LottoTickets manuallyPurchasedLottoTickets
            = getManuallyPurchasedLottoTicketsNumbersInputFromUser(numberOfManualPurchaseTickets);
        return new UserPurchase(purchasePrice, manuallyPurchasedLottoTickets);
    }

    private static int getPurchasePriceInputFromUser() {
        InputPrinter.printPurchasePriceInputGuideMessage();
        String purchasePriceInput = scanner.nextLine();
        return validateInteger(purchasePriceInput);
    }

    private static int getNumberOfManualPurchaseTickets() {
        InputPrinter.printNumberOfManualPurchaseTicketsInputGuideMessage();
        String numberOfManualPurchaseTicketsInput = scanner.nextLine();
        return validateInteger(numberOfManualPurchaseTicketsInput);
    }

    private static LottoTickets getManuallyPurchasedLottoTicketsNumbersInputFromUser(
        int numberOfManualPurchaseTickets) {
        InputPrinter.printLottoNumbersToManuallyPurchaseInputGuideMessage();
        LottoTickets manuallyPurchasedLottoTickets = new LottoTickets();
        for (int i = 0; i < numberOfManualPurchaseTickets; i++) {
            manuallyPurchasedLottoTickets.add(new LottoTicket(getLottoNumbersInput()));
        }
        return manuallyPurchasedLottoTickets;
    }

    private static int validateInteger(String purchasePriceInput) {
        if (!purchasePriceInput.matches(INTEGER_REGEX)) {
            throw new IllegalArgumentException("정수를 입력해주세요.");
        }
        return Integer.parseInt(purchasePriceInput);
    }

    public static WinningTicketAndBonusNumber getWinningTicketAndBonusNumber() {
        try {
            return getWinningTicketAndBonusNumberInputFromUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getWinningTicketAndBonusNumber();
        }
    }

    private static WinningTicketAndBonusNumber getWinningTicketAndBonusNumberInputFromUser() {
        InputPrinter.printWinnerLottoNumbersInputGuideMessage();
        List<LottoNumber> lottoNumbers = getLottoNumbersInput();
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        LottoNumber bonusNumber = getBonusNumberInput();
        return new WinningTicketAndBonusNumber(lottoTicket, bonusNumber);
    }

    private static List<LottoNumber> getLottoNumbersInput() {
        String winningNumbersInput = scanner.nextLine();
        validateAllIntegers(winningNumbersInput);
        return Arrays.stream(winningNumbersInput.split(LOTTO_NUMBER_DELIMITER))
            .map(inputNumber -> new LottoNumber(Integer.parseInt(inputNumber)))
            .collect(Collectors.toList());
    }

    private static void validateAllIntegers(String winningNumbersInput) {
        if (!Arrays.stream(winningNumbersInput.split(LOTTO_NUMBER_DELIMITER))
            .allMatch(name -> name.matches(INTEGER_REGEX))) {
            throw new IllegalArgumentException("각 로또 번호는 정수여야 합니다.");
        }
    }

    private static LottoNumber getBonusNumberInput() {
        InputPrinter.printBonusNumberInputGuideMessage();
        String bonusNumberInput = scanner.nextLine();
        int bonusNumber = validateInteger(bonusNumberInput);
        InputPrinter.printNewLine();
        return new LottoNumber(bonusNumber, true);
    }
}
