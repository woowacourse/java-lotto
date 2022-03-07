package model.generator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import model.lottonumber.Lotto;

public class LottoGenerator {

    private static final LottoGenerator LOTTO_GENERATOR = new LottoGenerator();

    private LottoGenerator() {
    }

    public static LottoGenerator getInstance() {
        return LOTTO_GENERATOR;
    }


    public List<Lotto> makeManualLotto(final List<List<Integer>> lottoNumberGroups) {
        return lottoNumberGroups.stream()
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    public List<Lotto> makeAutoLotto(final int autoLottoCount) {
        Generator generator = new LottoNumberGenerator();

        return IntStream.range(0, autoLottoCount)
                .mapToObj(index -> new Lotto(generator.generateNumbers()))
                .collect(Collectors.toList());
    }
}
