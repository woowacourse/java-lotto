package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Lottos {

    private final List<Lotto> lottos;

    Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lottos(int quantityOfAuto, List<Lotto> lottos) {
        IntStream.range(0, quantityOfAuto)
                .mapToObj(i -> Lotto.createByAuto())
                .forEach(lottos::add);
        this.lottos = lottos;
    }

    public Result getResult(WinningNumbers winningNumbers) {
        Result result = new Result();

        for (Lotto lotto : lottos) {
            Optional<Rank> winningPrice = winningNumbers.getRank(lotto);
            winningPrice.ifPresent(result::add);
        }

        return result;
    }

    public int getCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    @Override
    public String toString() {
        return "Lottos{" +
                "lottos=" + lottos +
                '}';
    }
}
