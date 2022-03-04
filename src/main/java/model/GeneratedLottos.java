package model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import model.generator.LottosGenerator;

public class GeneratedLottos {
    private List<Lotto> lottos;

    public GeneratedLottos(LottosGenerator generator) {
        this.lottos = Collections.unmodifiableList(generator.createLottos());
    }

    public LottoResult summary(WinningLottoNumbers winningLottoNumbers) {
        List<LottoRank> ranks = lottos.stream()
                .map(winningLottoNumbers::getRankBy)
                .collect(Collectors.toList());
        return new LottoResult(ranks);
    }


}
