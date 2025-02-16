package factory;

import java.util.List;
import model.Lotto;
import util.RandomGenerator;

public interface LottoFactory {
    Lotto createLotto();
    Lotto createLotto(List<Integer> numbers);
}
