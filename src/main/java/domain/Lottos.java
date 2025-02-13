package domain;

import domain.dto.GetLottoDto;
import domain.dto.GetLottosDto;
import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private List<Lotto> lottos;

    public Lottos(){
        lottos= new ArrayList<>();
    }

    public void add(Lotto lotto) {
        lottos.add(lotto);
    }


    public GetLottosDto getLottosDto() {
        List<GetLottoDto> getLottoDtos = new ArrayList<>();
        for (Lotto lotto : lottos) {
            getLottoDtos.add(lotto.getLottoDto());
        }
        return new GetLottosDto(getLottoDtos);
    }

    public void getResult(WinningLotto winningLotto) {
        List<Integer> matchCount = countMatchNumbers(winningLotto);

    }

    private List<Integer> countMatchNumbers(WinningLotto winningLotto) {
        List<Integer> matchCounts = new ArrayList<>();

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatchNumbers(winningLotto);
            matchCounts.add(matchCount);
        }

        return matchCounts;
    }
}
