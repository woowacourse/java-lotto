package lotto.domain;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos createByAuto(int quantity) {
        final List<Lotto> lottos = IntStream.range(0, quantity)
                .mapToObj(i -> Lotto.createByAuto())
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public static Lottos createByManual(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public static Lottos of(List<Lotto> lottos1, List<Lotto> lottos2) {
        final List<Lotto> joinedLottos = Stream.concat(lottos1.stream(), lottos2.stream())
                .collect(Collectors.toList());

        return new Lottos(joinedLottos);
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
}
