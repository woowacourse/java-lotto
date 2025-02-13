package model;

import dto.LottoDto;
import java.util.ArrayList;
import java.util.List;

public class UserLotto {
    private final int purchaseNumber;
    private final List<Lotto> lottos = new ArrayList<>();

    public UserLotto(String rawPurchaseAmount) {
        purchaseNumber = Integer.parseInt(rawPurchaseAmount) / 1000;
        for (int i = 0; i < purchaseNumber; i++) {
            lottos.add(new Lotto());
        }
    }

    public List<LottoDto> getLottosDto() {
        List<LottoDto> lottosDto = new ArrayList<>();
        for(Lotto lotto : lottos) {
            lottosDto.add(LottoDto.from(lotto));
        }
        return lottosDto;
    }
}
