package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoOMRCard {
    private List<Lotto> lottos;

    public LottoOMRCard() {
        lottos = new ArrayList<>();
    }

    public void addCustomLotto(String[] userNumbers) {
        lottos.add(CustomLottoGenerator.makeLotto(userNumbers));
    }

    LottoPaper getPaper() {
        return new LottoPaper(lottos);
    }

    int countOfLotto() {
        return lottos.size();
    }
}
