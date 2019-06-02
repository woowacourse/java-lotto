package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    private int numberOfLotto;
    private List<Lotto> lottos;

    public LottoTicket(String money){
        this.numberOfLotto = new Price(money).getNumberOfLotto();
        this.lottos = new ArrayList<>();
        createAutoLottoNumbers();
    }

    public AutoGenerateLotto createAutoLottoNumbers(){
        return new AutoGenerateLotto(numberOfLotto, lottos);
    }

    @Override
    public String toString() {
        return lottos.stream().map(Lotto::toString).collect(Collectors.joining("\n"));
    }

}
