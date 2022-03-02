package domain;

import static domain.Lotto.LOTTO_NUMBERS_SIZE;
import static domain.LottoNumber.LOTTO_NUMBERS_LIST;
import static util.LottoNumberUtils.getValidManuals;

import dto.LottoCountsDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;
    private final LottoCountsDto countsDto;

    private Lottos(List<Lotto> lottos, LottoCountsDto countsDto) {
        this.lottos = lottos;
        this.countsDto = countsDto;
    }

    public static Lottos of(List<String> manuals, LottoCountsDto lottoCountsDto) {
        List<Lotto> manualLottos = getValidManuals(manuals);
        int autosCount = lottoCountsDto.getAutos();

        List<Lotto> lottos = Stream.concat(manualLottos.stream(), autosStream(autosCount))
                .collect(Collectors.toList());

        return new Lottos(lottos, lottoCountsDto);
    }

    private static Stream<Lotto> autosStream(int autosCount) {
        return Stream.generate(() -> new Lotto(generateAutoNumbers()))
                .limit(autosCount);
    }

    private static List<LottoNumber> generateAutoNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LOTTO_NUMBERS_LIST);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, LOTTO_NUMBERS_SIZE);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getManuals() {
        return countsDto.getManuals();
    }

    public int getAutos() {
        return countsDto.getAutos();
    }

    @Override
    public String toString() {
        return "Lottos{" + "lottos=" + lottos + '}';
    }
}
