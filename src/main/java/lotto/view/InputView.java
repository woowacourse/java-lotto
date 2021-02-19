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

    public static UserPurchase getUserPurchase() throws IllegalArgumentException {
        InputPrinter.printPurchasePriceInputGuideMessage();
        String purchasePriceInput = scanner.nextLine();
        int purchasePrice = validateNaturalNumber(purchasePriceInput);
        return new UserPurchase(purchasePrice);
    }

    public static WinningLottoNumbers getWinningLottoNumbers() throws IllegalArgumentException {
        List<LottoNumber> lottoNumbers = getWinningLottoNumbersInput();
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        LottoNumber bonusNumber = getBonusNumberInput();
        return new WinningLottoNumbers(lottoTicket, bonusNumber);
    }

    private static int validateNaturalNumber(String purchasePriceInput) throws IllegalArgumentException {
        if (!purchasePriceInput.matches(NUMERIC_REGULAR_EXPRESSION)) {
            throw new IllegalArgumentException("자연수를 입력해주세요.");
        }
        return Integer.parseInt(purchasePriceInput);
    }

    private static List<LottoNumber> getWinningLottoNumbersInput() throws IllegalArgumentException {
        InputPrinter.printWinnerLottoNumbersInputGuideMessage();
        String winningNumbersInput = scanner.nextLine();
        validateAllNaturalNumbers(winningNumbersInput);
        return Arrays.stream(winningNumbersInput.split(LOTTO_NUMBER_DELIMITER))
            .map(inputNumber -> new LottoNumber(Integer.parseInt(inputNumber)))
            .collect(Collectors.toList());
    }

    private static void validateAllNaturalNumbers(String winningNumbersInput) throws IllegalArgumentException {
        if (!Arrays.stream(winningNumbersInput.split(LOTTO_NUMBER_DELIMITER))
            .allMatch(name -> name.matches(NUMERIC_REGULAR_EXPRESSION))) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다.");
        }
    }

    private static LottoNumber getBonusNumberInput() throws IllegalArgumentException {
        InputPrinter.printBonusNumberInputGuideMessage();
        String bonusNumberInput = scanner.nextLine();
        int bonusNumber = validateNaturalNumber(bonusNumberInput);
        InputPrinter.printNewLine();
        return new LottoNumber(bonusNumber);
    }
}
