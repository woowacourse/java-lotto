package model;

import dto.LottoDto;
import java.util.ArrayList;
import java.util.List;

public class UserLotto {
    private final int purchaseNumber;
    private final List<Lotto> lottos = new ArrayList<>();

    public UserLotto(String rawPurchaseAmount) {
        if(!isPurchaseNumber1000Unit(rawPurchaseAmount)) {
            throw new IllegalArgumentException("1000원 단위의 금액을 입력해주세요.\n");
        }

        purchaseNumber = Integer.parseInt(rawPurchaseAmount) / 1000;
        for (int i = 0; i < purchaseNumber; i++) {
            lottos.add(new Lotto());
        }
    }

    private boolean isPurchaseNumber1000Unit(String rawPurchaseAmount) {
        return ((Integer.parseInt(rawPurchaseAmount) % 1000 == 0));
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
