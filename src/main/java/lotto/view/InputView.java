package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.NumberOfTicketsToPurchaseManually;
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
            return getUserPurchaseInputFromUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getUserPurchase();
        }
    }

    private static UserPurchase getUserPurchaseInputFromUser() {
        PurchasePrice purchasePrice = getPurchasePriceInputFromUser();
        NumberOfTicketsToPurchaseManually numberOfTicketsToPurchaseManually
            = getNumberOfTicketsToPurchaseManually(purchasePrice);
        return new UserPurchase(purchasePrice, numberOfTicketsToPurchaseManually);
    }

    private static PurchasePrice getPurchasePriceInputFromUser() {
        InputPrinter.printInputGuideMessageOfPurchasePrice();
        String purchasePriceInput = scanner.nextLine();
        int purchasePrice = validateInteger(purchasePriceInput);
        return new PurchasePrice(purchasePrice);
    }

    private static NumberOfTicketsToPurchaseManually getNumberOfTicketsToPurchaseManually(
        PurchasePrice purchasePrice) {

        InputPrinter.printInputGuideMessageOfNumberOfManualPurchaseTickets();
        String numberOfTicketsToPurchaseManuallyInput = scanner.nextLine();
        int numberOfTicketsToPurchaseManually
            = validateInteger(numberOfTicketsToPurchaseManuallyInput);
        return new NumberOfTicketsToPurchaseManually(numberOfTicketsToPurchaseManually,
            purchasePrice);
    }

    private static int validateInteger(String purchasePriceInput) {
        if (!purchasePriceInput.matches(INTEGER_REGEX)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
        return Integer.parseInt(purchasePriceInput);
    }

    public static LottoTickets purchaseManually(UserPurchase userPurchase) {
        InputPrinter.printInputGuideMessageOfLottoNumbersToPurchaseManually();
        LottoTickets lottoTickets = new LottoTickets();
        for (int i = 0; i < userPurchase.manualTicketsSize(); i++) {
            lottoTickets.add(new LottoTicket(getLottoNumbersInput()));
        }
        return lottoTickets;
    }

    private static List<LottoNumber> getLottoNumbersInput() {
        String winningNumbersInput = scanner.nextLine();
        validateAllIntegerLottoNumbersInput(winningNumbersInput);
        return Arrays.stream(winningNumbersInput.split(LOTTO_NUMBERS_DELIMITER))
            .map(inputNumber -> new LottoNumber(Integer.parseInt(inputNumber)))
            .collect(Collectors.toList());
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
        InputPrinter.printInputGuideMessageOfWinnerLottoNumbers();
        List<LottoNumber> lottoNumbers = getLottoNumbersInput();
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        LottoNumber bonusNumber = getBonusNumberInput();
        return new WinningTicketAndBonusNumber(lottoTicket, bonusNumber);
    }

    private static void validateAllIntegerLottoNumbersInput(String winningNumbersInput) {
        if (!Arrays.stream(winningNumbersInput.split(LOTTO_NUMBERS_DELIMITER))
            .allMatch(name -> name.matches(INTEGER_REGEX))) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static LottoNumber getBonusNumberInput() {
        InputPrinter.printInputGuideMessageOfBonusNumber();
        String bonusNumberInput = scanner.nextLine();
        int bonusNumber = validateInteger(bonusNumberInput);
        InputPrinter.printNewLine();
        return new LottoNumber(bonusNumber);
    }
}
