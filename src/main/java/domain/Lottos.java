package domain;

import static constant.LottoConstants.LOTTO_PRICE;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public static Lottos of(int lottoCount) {
        List<Lotto> lottos = Stream.generate(Lotto::new)
                .limit(lottoCount)
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    @Override
    public String toString() {
        return "Lottos{" + "lottos=" + lottos + '}';
    }
}
