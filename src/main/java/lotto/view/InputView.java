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
    private static final String DEMAND_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String NOT_INTERGER_MESSAGE = "정수를 입력해 주세요.";
    private static final String REQUIRE_MANUAL_NUMBER_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String NOT_CORRECT_INPUT = "올바르지 않은 로또 번호 형식입니다.";
    private static final String REQUIRE_MANUAL_NUMBER_LIST__MESSAGE = "수동으로 구매할 로또 번호를 입력해 주세요.";
    public static int getInsertedMoney() {
        System.out.println(DEMAND_MONEY_MESSAGE);
        return getInterger();
    }

    private static int getInterger() {
        try {
            return Integer.parseInt(SCANNER.nextLine());
        } catch (Exception e) {
            System.out.println(NOT_INTERGER_MESSAGE);
            return getInsertedMoney();
        }
    }

    public static int getManualNum() {
        System.out.println(REQUIRE_MANUAL_NUMBER_MESSAGE);
        return getInterger();
    }


    public static List<List<Integer>> getManualNumbers(int manualNum) {
        List<List<Integer>> manualNumbers = new ArrayList<>();
        System.out.println(REQUIRE_MANUAL_NUMBER_LIST__MESSAGE);
        for (int i = 0; i < manualNum; i++) {
            manualNumbers.add(getMultipleInteger());
        }
        return manualNumbers;
    }

    private static List<Integer> getMultipleInteger() {
        try {
            String numbers = SCANNER.nextLine();
            Pattern.compile("\\d(\\d)?(,\\d(\\d)?)*").matcher(StringUtils.deleteWhitespace(numbers));
            return Arrays.asList(numbers
                    .split(","))
                    .stream()
                    .map(n -> Integer.parseInt(n))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(NOT_CORRECT_INPUT);
            return getMultipleInteger();
        }
    }


}
