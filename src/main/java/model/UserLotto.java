package model;

import dto.LottoDto;
import java.util.ArrayList;
import java.util.List;

public class UserLotto {
    private final int purchaseNumber;
    private final List<Lotto> lottos = new ArrayList<>();

    public UserLotto(String rawPurchaseAmount) {
        LottoMachine lottoMachine = new LottoMachine();
        purchaseNumber = Integer.parseInt(rawPurchaseAmount) / 1000;
        for (int i = 0; i < purchaseNumber; i++) {
            Lotto lotto = new Lotto(lottoMachine.generateLotto());
            lottos.add(lotto);
        }
    }

    public int getPurchaseAmount() {
        return purchaseNumber * 1000;
    }

    public List<LottoDto> getLottosDto() {
        List<LottoDto> lottosDto = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottosDto.add(LottoDto.from(lotto));
        }
        return lottosDto;
    }

    public List<LottoDto> getSortedLottosDto() {
        List<LottoDto> lottosDto = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottosDto.add(LottoDto.getSortedLottoDto(lotto));
        }
        return lottosDto;
    }
}
