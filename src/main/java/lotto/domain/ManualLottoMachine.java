package lotto.domain;

import lotto.util.ConvertLottoNumber;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoMachine implements LottoMachine {

    private final List<List<Integer>> lottoNumbersList;

    public ManualLottoMachine(List<List<Integer>> numbersList) {
        this.lottoNumbersList = new ArrayList<>(numbersList);
    }

    @Override
    public List<Lotto> generateLottos() {
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> lottoNumbers : lottoNumbersList) {
            lottos.add(new Lotto(ConvertLottoNumber.run(lottoNumbers)));
        }
        return lottos;
    }
}
