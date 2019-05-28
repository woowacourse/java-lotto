package lotto.domain;

import java.util.List;

public class LottoContainer {
    private List<Lotto> lottoContainer;

    public LottoContainer(List<Lotto> lottoContainer) {
        this.lottoContainer = lottoContainer;
    }

    public int getSize(){
        return lottoContainer.size();
    }
}
