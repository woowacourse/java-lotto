package lotto.model.creator.lottos;

import lotto.model.object.Lotto;

import java.util.List;

public interface LottosCreatorStrategy {
        List<Lotto> create();
}
