package domain;

import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos){
        this.lottos = lottos;
    }

    public Lotto getLottoByIndex(int index) {
        if (index < 0 || index >= lottos.size()) {
            throw new IndexOutOfBoundsException(index);
        }
        return lottos.get(index);
    }

    public boolean containByIndex(int idx, int number) {
        return lottos.get(idx).getNumbers().contains(number);
    }

    public int size() {
        return lottos.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        lottos.forEach(lotto -> sb.append(lotto.getNumbers()).append("\n"));
        return sb.toString();
    }
}
