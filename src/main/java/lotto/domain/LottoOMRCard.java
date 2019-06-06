package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoOMRCard {
    private List<Lotto> lottos;

    public LottoOMRCard() {
        lottos = new ArrayList<>();
    }

    public void addCustomLotto(String[] userNumbers) {
        lottos.add(new CustomLottoGenerator(userNumbers).makeLotto());
    }

    LottoPaper generatePaper(List<Lotto> lottos) {
        this.lottos.addAll(lottos);
        return new LottoPaper(this.lottos);
    }

    int countOfLotto() {
        return lottos.size();
    }
}
