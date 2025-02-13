package factory;

import java.util.List;
import model.Lotto;

public class LottoFactory {
    public static Lotto createLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }
}
