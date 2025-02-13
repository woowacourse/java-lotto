package domain;

import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos (List<Lotto> lottos){
        this.lottos = lottos;
    }

    public Lotto getLottoByIndex(int index) {
        if (index < 0 || index >= lottos.size()) {
            throw new IndexOutOfBoundsException(index);
        }
        return lottos.get(index);
    }

    public int size() {
        return lottos.size();
    }
}
