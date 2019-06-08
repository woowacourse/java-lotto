package lotto.domain;

import lotto.util.ConvertLottoNumber;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoMachine implements LottoMachine {

    private final List<String> lottoNumbersList;

    public ManualLottoMachine(List<String> numbersList) {
        this.lottoNumbersList = new ArrayList<>(numbersList);
    }

    @Override
    public List<Lotto> generateLottos() {
        List<Lotto> lottos = new ArrayList<>();
        for (String lottoNumbers : lottoNumbersList) {
            lottos.add(new Lotto(ConvertLottoNumber.run(lottoNumbers)));
        }
        return lottos;
    }
}