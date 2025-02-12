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
}
