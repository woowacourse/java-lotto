package lotto.domain;

import java.util.*;

public class WinnerNumber {
    private static final String SPLIT_COMMA = ",";
    private static final String ERROR_DUPLICATE = "중복이 있습니다.";

    private final Lotto winnerLotto;

    private WinnerNumber(String input) {
        List<Number> numbers = new ArrayList<>();
        String[] inputNumber = input.split(SPLIT_COMMA);

        if (validDuplicate(inputNumber)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE);
        }
        for (String str : inputNumber) {
            numbers.add(Number.create(Integer.parseInt(str)));
        }

        winnerLotto = Lotto.create(numbers);
    }

    public static WinnerNumber create(String input) {
        return new WinnerNumber(input);
    }

    public int getCount(Lotto lotto) {
        int count = 0;

        for (int i = 0; i < lotto.getSize(); i++) {
            count = addCount(lotto, count, i);
        }

        return count;
    }

    private int addCount(Lotto lotto, int count, int i) {
        if (winnerLotto.isContains(lotto.IndexByNumber(i))) {
            count++;
        }
        return count;
    }

    private boolean validDuplicate(String[] str) {
        Set<String> numbers = new HashSet<>(Arrays.asList(str));
        return numbers.size() != str.length;
    }
}
