package lotto.view;

import lotto.domain.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WebInputView {

    public static LottoBuyingMoney inputLottoBuyingMoney(String input) {
        return new LottoBuyingMoney(parseInt(input));
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("정수를 입력해 주세요.");
        }
    }

    public static LottoCount inputLottoCount(LottoBuyingMoney lottoBuyingMoney, String input) {
        return new LottoCount(lottoBuyingMoney, parseInt(input));
    }

    public static List<Integer> inputLotto(String input) {
        return parseInts(input);
    }

    private static List<Integer> parseInts(String input) {
        try {
            Pattern.compile("\\d(\\d)?(,\\d(\\d)?)*").matcher(StringUtils.deleteWhitespace(input));
            return Arrays.asList(input
                    .split(","))
                    .stream()
                    .map(n -> Integer.parseInt(n))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("정수를 콤마를 이용하여 구분해 입력해 주세요. (e.g., \"1, 2, 3, 4, 5, 6\")");
        }
    }

    public static WinningLotto inputWinningLotto(String input) {
        List<Integer> integers = parseInts(input);
        return new WinningLotto(integers.subList(0, 6), integers.get(6));
    }
}
