package model;

import dto.LottosResponse;

import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final int ticketAmount) {
        lottos = new ArrayList<>(generateLottos(ticketAmount));
    }

    public Lottos(final List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public LottosResponse createResponse() {
        return new LottosResponse(
                lottos.stream()
                        .map(Lotto::createResponse)
                        .toList()
        );
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    private List<Lotto> generateLottos(final int ticketAmount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < ticketAmount; i++) {
            lottos.add(new Lotto());
        }
        return lottos;
    }
}
