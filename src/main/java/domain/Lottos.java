package domain;

import static domain.Lotto.LOTTO_NUMBERS_SIZE;
import static domain.LottoNumber.LOTTO_NUMBERS_LIST;

import dto.LottoCountsDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import util.LottoNumberUtils;

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
        lottos.addAll(generateAutos(countsDto.getAutos()));

        return new Lottos(lottos, countsDto);
    }

    private static List<Lotto> getValidManuals(List<String> manualStrings) {
        if (manualStrings.isEmpty()) {
            return List.of();
        }

        return manualStrings.stream()
                .map(LottoNumberUtils::getValidLottoNumbers)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    private static List<Lotto> generateAutos(int autosCount) {
        List<LottoNumber> lottoNumbers = generateAutoNumbers();

        return Stream.generate(() -> new Lotto(lottoNumbers))
                .limit(autosCount)
                .collect(Collectors.toList());
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
