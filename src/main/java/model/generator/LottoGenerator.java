package model.generator;

import dto.LottoDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.lottonumber.Lotto;

public class LottoGenerator {

    private LottoGenerator() {
    }

    public static List<Lotto> makeLottos(final List<LottoDto> lottoNumberGroups, final int autoLottoCount) {
        List<Lotto> totalLottogroup = new ArrayList<>();
        totalLottogroup.addAll(makeManualLottos(lottoNumberGroups));
        totalLottogroup.addAll(makeAutoLottos(autoLottoCount));

        return totalLottogroup;
    }

    public static List<Lotto> makeManualLottos(final List<LottoDto> lottoNumberGroups) {
        return lottoNumberGroups.stream()
                .map(lottoNumberGroup -> new Lotto(lottoNumberGroup.getLottoNumbers()))
                .collect(Collectors.toList());
    }

    public static List<Lotto> makeAutoLottos(final int autoLottoCount) {
        Generator generator = new LottoNumberGenerator();

        return IntStream.range(0, autoLottoCount)
                .mapToObj(index -> new Lotto(generator))
                .collect(Collectors.toList());
    }
}
