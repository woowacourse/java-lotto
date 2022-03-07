package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {

    private final List<List<Integer>> lottoNumbers;

    public ManualLottoGenerator(List<List<Integer>> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    @Override
    public List<Lotto> generate() {
        List<Lotto> lottos = new ArrayList<>();

        for (List<Integer> lottoNumber : lottoNumbers) {
            lottos.add(
                new Lotto(lottoNumber.stream().map(LottoNumber::new).collect(Collectors.toList())));
        }

        return new ArrayList<>(lottos);
    }

}
