package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoTickets {

    private final int buyMoney;
    private final List<Lotto> lottoTickets;

    public LottoTickets(int value) {
        this.buyMoney = value;
        lottoTickets = createLottoTickets();
    }

    private List<Lotto> createLottoTickets() {
        return new ArrayList<>();
    }

    public List<Lotto> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoTickets that = (LottoTickets) o;
        return buyMoney == that.buyMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyMoney);
    }
}
