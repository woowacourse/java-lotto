package model;

import static constant.LottoConstant.LOTTO_PRICE;

import java.util.List;
import java.util.stream.Stream;

public record Lottos(List<Lotto> lottos) {

    public int computeTicketCount() {
        return lottos.size();
    }

    public int computeTicketAmount() {
        return computeTicketCount() * LOTTO_PRICE;
    }

    public Stream<Lotto> stream() {
        return lottos.stream();
    }
}
