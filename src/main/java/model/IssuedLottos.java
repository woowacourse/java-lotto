package model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.generator.LottosGenerator;

public class IssuedLottos {
    private final List<Lotto> lottos;

    public IssuedLottos(List<Lotto> lottos) {
        this.lottos = Collections.unmodifiableList(lottos);
    }

    public static IssuedLottos generatedBy(LottosGenerator generator) {
        return new IssuedLottos(Collections.unmodifiableList(generator.createLottos()));
    }

    public static IssuedLottos merge(IssuedLottos from, IssuedLottos to) {
        List<Lotto> collect = Stream.of(from, to)
                .flatMap(issuedLottos -> issuedLottos.lottos.stream())
                .collect(Collectors.toList());
        return new IssuedLottos(collect);
    }

    public LottoResult summarize(WinningLottoNumbers winningLottoNumbers) {
        return lottos.stream()
                .map(winningLottoNumbers::getRankBy)
                .collect(collectingAndThen(toList(), LottoResult::new));
    }

    public int getLottosCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
