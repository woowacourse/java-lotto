package domain;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator implements LottoGenerator{

    private int amount;
    private List<List<Integer>> lottoNumbers;

    public ManualLottoGenerator(int amount, List<List<Integer>> lottoNumbers) {
        this.amount = amount;
        this.lottoNumbers = lottoNumbers;
    }

    @Override
    public List<Lotto> generate() {

        List<Lotto> lottos = new ArrayList<>();

        for (List<Integer> lottoNumber : lottoNumbers) {
            lottos.add(new Lotto(lottoNumber));
        }

        return lottos;
    }

}
