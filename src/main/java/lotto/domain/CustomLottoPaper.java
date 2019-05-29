package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class CustomLottoPaper {
    private List<Lotto> lottos;

    public CustomLottoPaper() {
        lottos = new ArrayList<>();
    }

    public void addCustomLotto(String[] userNumbers) {
        lottos.add(CustomLottoGenerator.makeLotto(userNumbers));
    }

    public LottoPaper getPaper() {
        return new LottoPaper(lottos);
    }
}
