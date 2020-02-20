package lotto.domain;

import java.util.List;

public class Lotties {
    private List<Lotto> lotties;

    public Lotties(List<Lotto> lotties){
        this.lotties = lotties;
    }

    public List<Lotto> getLotties() {
        return lotties;
    }
}
