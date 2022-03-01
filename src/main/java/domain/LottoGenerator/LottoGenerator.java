package domain.LottoGenerator;

import domain.Lotto.Lotto;

import java.util.List;

public interface LottoGenerator {

    Lotto generateLotto();

    Lotto generateLotto(List<Integer> numbers);
}
