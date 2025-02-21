package factory;

import java.util.List;
import model.Lotto;

public interface LottoFactory {
    Lotto createLotto();

    Lotto createLotto(List<Integer> numbers);
}
