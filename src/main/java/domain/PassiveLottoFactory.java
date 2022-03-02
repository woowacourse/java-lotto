package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PassiveLottoFactory implements LottoFactory{

    List<Lotto> lottos;

    public PassiveLottoFactory(List<List<LottoNumber>> passiveLottoNumbers) {
        this.lottos = new ArrayList<>(toLotto(passiveLottoNumbers));
    }

    private List<Lotto> toLotto(List<List<LottoNumber>> passiveLottoNumbers) {
        return passiveLottoNumbers.stream()
                .map(LottoNumbers -> new Lotto(LottoNumbers))
                .collect(Collectors.toList());
    }

    @Override
    public List<Lotto> create() {
        return lottos;
    }
}
