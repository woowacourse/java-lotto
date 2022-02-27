package domain;

import dto.LottoCountsDto;
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

    public static Lottos of(List<Lotto> manuals, LottoCountsDto lottoCountsDto) {
        int autosCount = lottoCountsDto.getAutos();

        List<Lotto> lottos = Stream.concat(manuals.stream(), autosStream(autosCount))
                .collect(Collectors.toList());

        return new Lottos(lottos, lottoCountsDto);
    }

    private static Stream<Lotto> autosStream(int autosCount) {
        return Stream.generate(Lotto::new)
                .limit(autosCount);
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
