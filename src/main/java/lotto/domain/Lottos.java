package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lotto getLotto(int index) {
        return lottos.get(index);
    }

    public List<Rank> match(WinningLotto winningLotto) {
        List<Rank> ranks = new ArrayList<>();
        for(int i = 0; i < lottos.size(); i++) {
             ranks.add(winningLotto.matchLotto(lottos.get(i)));
        }
        return ranks;
    }

    public int getLottosSize(){
        return lottos.size();
    }
}
