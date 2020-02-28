package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class LottoTicket implements Iterable<Lotto> {
	private List<Lotto> lottoTicket;

	public LottoTicket() {
		lottoTicket = new ArrayList<>();
	}

	public LottoTicket(List<Lotto> lottoTicket) {
		this.lottoTicket = new ArrayList<>(lottoTicket);
	}

	public LottoTicket add(LottoTicket inputLottoTicket){
		for(Lotto lotto : inputLottoTicket) {
			lottoTicket.add(lotto);
		}
		return new LottoTicket(lottoTicket);
	}

	@Override
	public Iterator<Lotto> iterator() {
		return lottoTicket.iterator();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket lottoTicket1 = (LottoTicket) o;
        return lottoTicket.equals(lottoTicket1.lottoTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTicket);
    }
}
