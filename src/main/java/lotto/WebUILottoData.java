package lotto;

import lotto.domain.lotto.NumberOfCustomLotto;
import lotto.domain.lotto.Price;

public class WebUILottoData {
    private Price price;
    private NumberOfCustomLotto numberOfCustomLotto;

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
}
