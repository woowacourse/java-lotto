package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.generator.LottosGenerator;

public class IssuedLottos {
    private List<Lotto> lottos;

    public IssuedLottos(LottosGenerator generator) {
        this.lottos = Collections.unmodifiableList(generator.createLottos());
    }

    public static IssuedLottos merge(IssuedLottos from, IssuedLottos to) {
        List<Lotto> collect = Stream.of(from, to)
                .flatMap(issuedLottos -> issuedLottos.getLottos().stream())
                .collect(Collectors.toList());
        return new IssuedLottos(() -> collect);
    }

    public LottoResult summary(WinningLottoNumbers winningLottoNumbers) {
        List<LottoRank> ranks = lottos.stream()
                .map(winningLottoNumbers::getRankBy)
                .collect(Collectors.toList());
        return new LottoResult(ranks);
    }

    public int getLottosCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
