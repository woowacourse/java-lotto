package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoTicket;
import lotto.domain.NumberOfCustomLotto;
import lotto.domain.Price;

import java.util.List;

public class WebUILottoData {
    private Price price;
    private NumberOfCustomLotto numberOfCustomLotto;
    private Lotto lotto;
    private List<Lotto> lottos;

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public NumberOfCustomLotto getNumberOfCustomLotto() {
        return numberOfCustomLotto;
    }

    public void setNumberOfCustomLotto(NumberOfCustomLotto numberOfCustomLotto) {
        this.numberOfCustomLotto = numberOfCustomLotto;
    }

    public Lotto getLotto() {
        return lotto;
    }

    public void setLotto(Lotto lotto) {
        this.lotto = lotto;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public void setLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }
}
