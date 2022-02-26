package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.model.number.LottoNumber;

/*
 * 자동 로또 한 장을 의미하는 Class
 */
public class Lotto {
    private static final int NUMBER_COUNT = 6;
    private static final int PRICE = 1000;

    private List<LottoNumber> numbers;

    public Lotto() {
        drawNumbers();
        Collections.sort(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void drawNumbers() {
        numbers = new ArrayList<>();
        for (int i = 0; i < NUMBER_COUNT; i++) {
            numbers.add(LottoNumber.draw());
        }
    }

    public static int countAvailableTickets(Money money) {
        return money.countAvailable(PRICE);
    }

    public boolean contains(LottoNumber number) {
        return numbers.contains(number);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }
}
