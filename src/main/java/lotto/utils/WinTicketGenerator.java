package lotto.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.domain.LottoNumber;

public class WinTicketGenerator {
    private static final String REQUEST_NON_EMPTY_INPUT = "빈 문자를 입력할 수 없습니다.";
    private static final String REQUEST_NON_DUPLICATED_NUMBER = "중복되지 않은 숫자 6개를 입력해주세요.";
    private static final String REQUEST_DELIMITER_MESSAGE = ",(콤마)와 공백으로 구분된 숫자 6자리를 입력해주세요. ex) 1, 2, 3, 4, 5, 6";
    private static final String DELIMITER = ", ";
    private static final int LOTTO_SIZE = 6;

    public static Set<LottoNumber> generate(String input) {
        checkEmpty(input);
        List<String> splitWinNumbers = List.of(input.split(DELIMITER, -1));
        checkDelimiter(splitWinNumbers.size());
        Set<LottoNumber> winNumbers = createWinNumbers(splitWinNumbers);
        checkDuplicate(splitWinNumbers.size(), winNumbers.size());
        return winNumbers;
    }

    private static Set<LottoNumber> createWinNumbers(List<String> splitWinNumbers) {
        Set<LottoNumber> winNumbers = new HashSet<>();
        for (String winNumber : splitWinNumbers) {
            addWinLottoNumbers(winNumbers, winNumber);
        }
        return winNumbers;
    }

    private static void checkEmpty(String winNumbers) {
        if (winNumbers == null || winNumbers.isBlank()) {
            throw new IllegalArgumentException(REQUEST_NON_EMPTY_INPUT);
        }
    }

    private static void checkDelimiter(int size) {
        if (size != LOTTO_SIZE) {
            throw new IllegalArgumentException(REQUEST_DELIMITER_MESSAGE);
        }
    }

    private static void addWinLottoNumbers(Set<LottoNumber> winLottoNumbers, String win) {
        try {
            winLottoNumbers.add(new LottoNumber(Integer.parseInt(win)));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }

    private static void checkDuplicate(int originalSize, int newSize) {
        if (originalSize != newSize) {
            throw new IllegalArgumentException(REQUEST_NON_DUPLICATED_NUMBER);
        }
    }
}
