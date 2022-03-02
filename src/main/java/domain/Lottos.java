package domain;

import static util.LottoUtils.generateAutos;
import static util.LottoUtils.getValidManuals;

import dto.LottoCountsDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.LottoUtils;

public class Lottos {

    private final List<Lotto> lottos;
    private final LottoCountsDto countsDto;

    private Lottos(List<Lotto> lottos, LottoCountsDto countsDto) {
        this.lottos = lottos;
        this.countsDto = countsDto;
    }

    public static Lottos of(List<String> manualsRaw, LottoCountsDto countsDto) {
        List<Lotto> lottos = new ArrayList<>();

        lottos.addAll(getValidManuals(manualsRaw));
        lottos.addAll(generateAutos(countsDto.getAutos(), LottoUtils::generateAutoNumbers));

        return new Lottos(lottos, countsDto);
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
