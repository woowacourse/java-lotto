package lotto.domain.game;

import java.util.List;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Number;

interface LottoGenerator {
    Lotto generate(List<Number> numbers);
}