package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.domain.ticketresult.WinningTicketAndBonusNumber;
import lotto.view.printer.InputPrinter;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NATURAL_NUMBER_REGEX = "[1-9]\\d*";
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
        InputPrinter.printPurchasePriceInputGuideMessage();
        String purchasePriceInput = scanner.nextLine();
        int purchasePrice = validateNaturalNumber(purchasePriceInput);

        InputPrinter.printManualPurchaseInputGuideMessage();

        return new UserPurchase(purchasePrice);
    }

    private static int validateNaturalNumber(String purchasePriceInput) {
        if (!purchasePriceInput.matches(NATURAL_NUMBER_REGEX)) {
            throw new IllegalArgumentException("자연수를 입력해주세요.");
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
        List<LottoNumber> lottoNumbers = getWinningLottoNumbersInput();
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        LottoNumber bonusNumber = getBonusNumberInput();
        return new WinningTicketAndBonusNumber(lottoTicket, bonusNumber);
    }

    private static List<LottoNumber> getWinningLottoNumbersInput() {
        InputPrinter.printWinnerLottoNumbersInputGuideMessage();
        String winningNumbersInput = scanner.nextLine();
        validateAllNaturalNumbers(winningNumbersInput);
        return Arrays.stream(winningNumbersInput.split(LOTTO_NUMBER_DELIMITER))
            .map(inputNumber -> new LottoNumber(Integer.parseInt(inputNumber)))
            .collect(Collectors.toList());
    }

    private static void validateAllNaturalNumbers(String winningNumbersInput) {
        if (!Arrays.stream(winningNumbersInput.split(LOTTO_NUMBER_DELIMITER))
            .allMatch(name -> name.matches(NATURAL_NUMBER_REGEX))) {
            throw new IllegalArgumentException("각 로또 번호는 자연수여야 합니다.");
        }
    }

    private static LottoNumber getBonusNumberInput() {
        InputPrinter.printBonusNumberInputGuideMessage();
        String bonusNumberInput = scanner.nextLine();
        int bonusNumber = validateNaturalNumber(bonusNumberInput);
        InputPrinter.printNewLine();
        return new LottoNumber(bonusNumber, true);
    }
}
