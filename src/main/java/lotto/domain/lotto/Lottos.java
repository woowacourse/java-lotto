package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Lottos implements Iterable<Lotto> {
	private List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		this.lottos = new ArrayList<>(lottos);
	}

	public Lottos add(Lottos inputLottos){
		for(Lotto lotto : inputLottos) {
			lottos.add(lotto);
		}
		return new Lottos(lottos);
	}

	@Override
	public Iterator<Lotto> iterator() {
		return lottos.iterator();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottos lottos1 = (Lottos) o;
        return lottos.equals(lottos1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
