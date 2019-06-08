package lotto.domain;

import java.util.List;

public class Lottoes {
    private final List<Lotto> lottos;

    public Lottoes(List<Lotto> lottos, int round) {
        this.lottos = lottos;
        createAutoLottos(round);
    }

    public int getSize() {
        return lottos.size();
    }

    public Lotto getIndexByLotto(int index) {
        return lottos.get(index);
    }

    private void createAutoLottos(int round) {
        NumberGenerator numberGenerator = NumberGenerator.create();

        for (int i = 0; i < round; i++) {
            List<Number> list = numberGenerator.getNumbers();
            lottos.add(new Lotto(list));
        }
    }
}
