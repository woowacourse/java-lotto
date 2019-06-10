package lotto.view;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int getLottoBuyingMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return getInteger();
    }

    private static int getInteger() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (Exception e) {
            System.out.println("정수를 입력해 주세요.");
            return getLottoBuyingMoney();
        }
    }

    public static int getNumOfCustomLottos() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return getInteger();
    }

    public static List<Integer> getWinningNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return getIntegers();
    }

    private static List<Integer> getIntegers() {
        try {
            String input = SCANNER.nextLine();
            Pattern.compile("\\d(\\d)?(,\\d(\\d)?)*").matcher(StringUtils.deleteWhitespace(input));
            return Arrays.asList(input
                    .split(","))
                    .stream()
                    .map(n -> Integer.parseInt(n))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("정수를 콤마를 이용하여 구분해 입력해 주세요. (e.g., \"1, 2, 3, 4, 5, 6\")");
            return getIntegers();
        }
    }

    public static int getBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return getInteger();
    }

    public static List<List<Integer>> getCustomLottoNumbers(int numOfCustomLottos) {
        List<List<Integer>> lottoNumbers = new ArrayList<>();

        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < numOfCustomLottos; i++) {
            lottoNumbers.add(getIntegers());
        }

        return lottoNumbers;
    }
}
