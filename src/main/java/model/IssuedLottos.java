package model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.generator.LottosGenerator;

public class IssuedLottos {
    private final List<Lotto> lottos;

    private IssuedLottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static IssuedLottos generatedBy(LottosGenerator generator) {
        return new IssuedLottos(generator.createLottos());
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
        return new ArrayList<>(lottos);
    }
}
