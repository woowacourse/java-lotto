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

    public static UserPurchase getUserPurchase() {
        InputPrinter.printPurchasePriceInputGuideMessage();
        String purchasePriceInput = scanner.nextLine();
        int purchasePrice = parseNumericInput(purchasePriceInput);
        return new UserPurchase(purchasePrice);
    }

    public static WinningLottoNumbers getWinningLottoNumbers() {
        List<LottoNumber> lottoNumbers = getWinningLottoNumbersInput();
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        LottoNumber bonusNumber = getBonusNumberInput();
        return new WinningLottoNumbers(lottoTicket, bonusNumber);
    }

    private static int parseNumericInput(String inputValue) {
        if (!inputValue.matches(NUMERIC_REGULAR_EXPRESSION)) {
            throw new IllegalArgumentException("자연수를 입력해주세요.");
        }
        return Integer.parseInt(inputValue);
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
            .allMatch(name -> name.matches(NUMERIC_REGULAR_EXPRESSION))) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다.");
        }
    }

    private static LottoNumber getBonusNumberInput() {
        InputPrinter.printBonusNumberInputGuideMessage();
        String bonusNumberInput = scanner.nextLine();
        int bonusNumber = parseNumericInput(bonusNumberInput);
        InputPrinter.printNewLine();
        return new LottoNumber(bonusNumber);
    }
}
