package model;

import dto.LottosResponse;
import model.lotto.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final int amount) {
        lottos = generateLottos(amount);
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

    private List<Lotto> generateLottos(final int amount) {
        ArrayList<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            lottos.add(new Lotto());
        }

        return lottos;
    }
}
