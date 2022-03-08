package model.generator;

import java.util.List;
import java.util.stream.Collectors;
import model.Lotto;

public class ManualGenerator implements LottosGenerator {

    private final List<Lotto> lottos;

    public ManualGenerator(List<List<Integer>> lottos) {
        this.lottos = lottos.stream()
                .map(Lotto::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<Lotto> createLottos() {
        return lottos;
    }
}
