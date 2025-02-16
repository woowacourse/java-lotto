package model;

import dto.LottosResponse;
import model.lotto.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final int ticketAmount) {
        lottos = new ArrayList<>();
        addLottos(ticketAmount);
    }

    public LottosResponse createResponse() {
        return new LottosResponse(
                lottos.stream()
                        .map(Lotto::createResponse)
                        .toList()
        );
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    private void addLottos(final int ticketAmount) {
        for (int i = 0; i < ticketAmount; i++) {
            lottos.add(new Lotto());
        }
    }
}
