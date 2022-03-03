package domain;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {

    private final List<List<Integer>> lottoNumbers;

    public ManualLottoGenerator(List<List<Integer>> lottoNumbers) {
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
    }

    @Override
    public List<Lotto> generate() {
        List<Lotto> lottos = new ArrayList<>();

        for (List<Integer> lottoNumber : lottoNumbers) {
            lottos.add(new Lotto(lottoNumber));
        }

        return new ArrayList<>(lottos);
    }

}
