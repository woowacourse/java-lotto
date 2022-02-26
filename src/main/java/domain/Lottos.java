package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;
    private final LottoCountsDto lottoCountsDto;

    private Lottos(List<Lotto> lottos, LottoCountsDto lottoCountsDto) {
        this.lottos = lottos;
        this.lottoCountsDto = lottoCountsDto;
    }

    public static Lottos of(List<Lotto> manuals, LottoCountsDto lottoCountsDto) {
        int randomCount = lottoCountsDto.getRandoms();

        List<Lotto> lottos = Stream.concat(manuals.stream(), randomLottosStream(randomCount))
                .collect(Collectors.toList());

        return new Lottos(lottos, lottoCountsDto);
    }

    private static Stream<Lotto> randomLottosStream(int randomCount) {
        return Stream.generate(Lotto::new)
                .limit(randomCount);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getManuals() {
        return lottoCountsDto.getManuals();
    }

    public int getRandoms() {
        return lottoCountsDto.getRandoms();
    }

    @Override
    public String toString() {
        return "Lottos{" + "lottos=" + lottos + '}';
    }
}
