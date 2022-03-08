package model.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.IssuedLottos;
import model.Lotto;

public class MergeGenerator implements LottosGenerator {
    private final IssuedLottos from;
    private final IssuedLottos to;

    public MergeGenerator(IssuedLottos from, IssuedLottos to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public List<Lotto> createLottos() {
        return Stream.of(from, to)
                .flatMap(issuedLottos -> issuedLottos.getLottos().stream())
                .collect(Collectors.toList());
    }
}
