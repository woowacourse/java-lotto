package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.ManualTicketsSize;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.WinningTicketAndBonusNumber;
import lotto.view.printer.InputPrinter;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INTEGER_REGEX = "\\d+";
    public static final String LOTTO_NUMBERS_DELIMITER = ", ";

    private InputView() {
    }

    public static UserPurchase getUserPurchase() {
        try {
            return getUserPurchaseInput();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getUserPurchase();
        }
    }

    private static UserPurchase getUserPurchaseInput() {
        PurchasePrice purchasePrice = getPurchasePriceInput();
        ManualTicketsSize manualTicketsSize = getManualTicketsSize(purchasePrice);
        return new UserPurchase(purchasePrice, manualTicketsSize);
    }

    private static PurchasePrice getPurchasePriceInput() {
        InputPrinter.printPurchasePriceInputMessage();
        String purchasePriceInput = scanner.nextLine();
        int purchasePrice = validateInteger(purchasePriceInput);
        return new PurchasePrice(purchasePrice);
    }

    private static ManualTicketsSize getManualTicketsSize(PurchasePrice purchasePrice) {
        InputPrinter.printManualTicketsSizeInputMessage();
        String numberOfTicketsToPurchaseManuallyInput = scanner.nextLine();
        int manualTicketsSize = validateInteger(numberOfTicketsToPurchaseManuallyInput);
        return new ManualTicketsSize(manualTicketsSize, purchasePrice);
    }

    private static int validateInteger(String purchasePriceInput) {
        if (!purchasePriceInput.matches(INTEGER_REGEX)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        return Integer.parseInt(purchasePriceInput);
    }

    public static LottoTickets purchaseManually(UserPurchase userPurchase) {
        try {
            return getManualLottoTickets(userPurchase);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return purchaseManually(userPurchase);
        }
    }

    private static LottoTickets getManualLottoTickets(UserPurchase userPurchase) {
        InputPrinter.printManualLottoNumbersInputMessage();
        LottoTickets lottoTickets = new LottoTickets();
        for (int i = 0; i < userPurchase.manualTicketsSize(); i++) {
            lottoTickets.add(new LottoTicket(getLottoNumbersInput()));
        }
        return lottoTickets;
    }

    private static List<LottoNumber> getLottoNumbersInput() {
        String lottoNumbersInput = scanner.nextLine();
        validateAllIntegerLottoNumbersInput(lottoNumbersInput);
        return Arrays.stream(lottoNumbersInput.split(LOTTO_NUMBERS_DELIMITER))
            .map(numberInput -> LottoNumbers.get(Integer.parseInt(numberInput)))
            .collect(Collectors.toList());
    }

    private static void validateAllIntegerLottoNumbersInput(String winningNumbersInput) {
        if (!Arrays.stream(winningNumbersInput.split(LOTTO_NUMBERS_DELIMITER))
            .allMatch(number -> number.matches(INTEGER_REGEX))) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public static WinningTicketAndBonusNumber getWinningTicketAndBonusNumber() {
        try {
            return getWinningTicketAndBonusNumberInput();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getWinningTicketAndBonusNumber();
        }
    }

    private static WinningTicketAndBonusNumber getWinningTicketAndBonusNumberInput() {
        InputPrinter.printWinnerLottoNumbersInputMessage();
        LottoTicket winnerTicket = new LottoTicket(getLottoNumbersInput());
        LottoNumber bonusNumber = getBonusNumberInput();
        return new WinningTicketAndBonusNumber(winnerTicket, bonusNumber);
    }

    private static LottoNumber getBonusNumberInput() {
        InputPrinter.printBonusNumberInputMessage();
        String bonusNumberInput = scanner.nextLine();
        int bonusNumber = validateInteger(bonusNumberInput);
        InputPrinter.printNewLine();
        return LottoNumbers.get(bonusNumber);
    }
}
