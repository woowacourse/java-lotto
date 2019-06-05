package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    private static List<Number> numbers;

    static {
        numbers = Number.generateAllNumbers();
    }

    // 테스트를 어떻게 해야할까?
    // 어떤 식으로 테스트를 하는게 맞는건가?
    public static Lotto createRandomLotto() {
        Collections.shuffle(numbers);

        List<Number> tmpNumbers = new ArrayList<>(numbers.subList(0, 6));
        Collections.sort(tmpNumbers);

        return new Lotto(tmpNumbers);
    }

    public static Lotto createLottoFromNumbers(List<Number> numbers) {
        List<Number> tmpNumbers = new ArrayList<>(numbers);
        Collections.sort(tmpNumbers);

        return new Lotto(tmpNumbers);
    }
}
