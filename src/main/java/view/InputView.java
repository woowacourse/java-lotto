package view;

import domain.LottoNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoConstant.MAXIMUM_LOTTO_NUMBER;
import static constant.LottoConstant.MINIMUM_LOTTO_NUMBER;

public class InputView {

    private static final String SPACE = " ";
    private static final String BLANK = "";
    private static final String SPLITTER = ",";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new AssertionError();
    }

    public static int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return getNumber();
    }

    public static List<Integer> getWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();

        return Arrays.stream(input.replaceAll(SPACE, BLANK).split(SPLITTER))
                .peek(InputView::validateNumber)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public static int getManualPurchaseCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return getNumber();
    }

    public static int getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return getNumber();
    }

    private static int getNumber() {
        String input = scanner.nextLine();
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateNumber(String inputMoney) {
        try {
            Integer.parseInt(inputMoney);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("입력은 숫자여야 합니다.");
        }
    }

    public static List<List<Integer>> getManualLottoNumbers(int manualCount) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<List<Integer>> manualLottoNumbers = new ArrayList<>();

        for (int i = 0; i < manualCount; i++) {
            String input = scanner.nextLine();
            manualLottoNumbers.add(readLottoNumber(input));
        }

        return manualLottoNumbers;
    }

    public static List<Integer> readLottoNumber(String numbers) {
        return Arrays.stream(numbers.replaceAll(SPACE, BLANK).split(SPLITTER))
                .peek(InputView::validateNumber)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }
}
