package lotto.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.LottoNumber;
import lotto.domain.vo.ManualPurchaseCount;
import lotto.domain.vo.WinningNumbers;
import lotto.dto.RequestManualLottoNumbers;
import lotto.dto.RequestPurchaseMoneyDto;

public class InputView {

    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String ERROR_NO_INPUT_MESSAGE = "입력이 없습니다.";
    private static final String ERROR_INPUT_EMPTY_MESSAGE = "공백 또는 빈 문자열이 입력하면 안됩니다.";
    private static final String ERROR_NOT_NUMBER_MESSAGE = "숫자 이외의 문자가 입력하면 안됩니다.";
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_PURCHASE_COUNT_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_PURCHASE_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_LOTTO_NUMBER_DELIMITER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    public static RequestPurchaseMoneyDto requestPurchaseMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        String input = nextLine();
        validateEmpty(input);
        System.out.println();
        return new RequestPurchaseMoneyDto(toInt(input));
    }

    private static String nextLine() {
        String input;
        try {
            input = scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(ERROR_NO_INPUT_MESSAGE);
        }
        return input;
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ERROR_INPUT_EMPTY_MESSAGE);
        }
    }

    private static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(ERROR_NOT_NUMBER_MESSAGE);
        }
    }

    public static RequestManualLottoNumbers requestManualLottoNumbers() {
        ManualPurchaseCount manualPurchaseCount = requestManualPurchaseCount();
        List<List<LottoNumber>> manualLottoNumbers = new ArrayList<>();
        if (manualPurchaseCount.canBuy()) {
            System.out.println(INPUT_MANUAL_PURCHASE_NUMBER_MESSAGE);
            manualLottoNumbers = readManualLottoNumbers(manualPurchaseCount);
            System.out.println();
        }
        return new RequestManualLottoNumbers(manualLottoNumbers);
    }

    private static ManualPurchaseCount requestManualPurchaseCount() {
        System.out.println(INPUT_MANUAL_PURCHASE_COUNT_MESSAGE);
        String input = nextLine();
        validateEmpty(input);
        System.out.println();
        return new ManualPurchaseCount(toInt(input));
    }

    private static List<List<LottoNumber>> readManualLottoNumbers(ManualPurchaseCount manualPurchaseCount) {
        List<List<LottoNumber>> manualLottoNumbers = new ArrayList<>();
        for (int i = 0; i < manualPurchaseCount.get(); i++) {
            manualLottoNumbers.add(toLottoNumberList(nextLine()));
        }
        return manualLottoNumbers;
    }

    public static WinningNumbers requestWinningNumber() {
        return new WinningNumbers(new Lotto(requestWinningNumbers()), requestBonusNumber());
    }

    private static List<LottoNumber> requestWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        String input = nextLine();
        return toLottoNumberList(input);
    }

    private static List<LottoNumber> toLottoNumberList(String input) {
        return Arrays.stream(input.split(INPUT_LOTTO_NUMBER_DELIMITER))
                .map(value -> toInt(value.trim()))
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private static LottoNumber requestBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
        int bonusNumber = toInt(nextLine());
        System.out.println();
        return new LottoNumber(bonusNumber);
    }
}
