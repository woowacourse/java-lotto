package lotto.domain;

import java.util.List;

public class Lottoes {
    private final List<Lotto> lottos;

    public Lottoes(List<Lotto> lottos, int round, NumberGenerator numberGenerator) {
        this.lottos = lottos;
        createAutoLottos(numberGenerator, round);
    }

    public int getSize() {
        return lottos.size();
    }

    public Lotto getIndexByLotto(int index) {
        return lottos.get(index);
    }

    private void createAutoLottos(NumberGenerator numberGenerator, int round) {
        for (int i = 0; i < round; i++) {
            lottos.add(new Lotto(numberGenerator.getNumbers()));
        }
    }
}
