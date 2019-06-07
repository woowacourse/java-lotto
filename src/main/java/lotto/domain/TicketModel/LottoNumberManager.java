package lotto.domain.TicketModel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class LottoNumberManager {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_COUNT = 6;
    private static List<Integer> numbers;

    static {
        numbers = new ArrayList<>();
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            numbers.add(i);
        }
    }

    private LottoNumberManager() {

    }

    public static List<Integer> autoNumber() {
        Collections.shuffle(numbers);
        return numbers.subList(0, NUMBER_COUNT);
    }

    public static boolean check(List<Integer> numbers) {
        if (numbers.size() != NUMBER_COUNT) {
            return false;
        }
        if (new HashSet<>(numbers).size() != NUMBER_COUNT) {
            return false;
        }
        if (Collections.min(numbers) < MIN_NUMBER || Collections.max(numbers) > MAX_NUMBER) {
            return false;
        }
        return true;
    }

    public static LottoNumbers validate(List<Integer> numbers) {
        return new LottoNumbers(numbers);
    }
}
