package domain;

import java.util.ArrayList;
import java.util.List;

public interface LottosGenerator {
    int START_INDEX = 0;

    List<Lotto> generateLottos(final LottoCount lottoCount);
}
