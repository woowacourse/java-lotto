package model;

import dto.LottoDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserLotto {
    private static final String PURCHASE_AMOUNT_1000_UNIT_EXCEPTION = "1000원 단위의 금액을 입력해주세요.\n";
    private final int purchaseNumber;
    private final List<Lotto> lottos = new ArrayList<>();

    public UserLotto(String rawPurchaseAmount) {
        if(!isPurchaseNumber1000Unit(rawPurchaseAmount)) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_1000_UNIT_EXCEPTION);
        }

        purchaseNumber = Integer.parseInt(rawPurchaseAmount) / 1000;
        for (int i = 0; i < purchaseNumber; i++) {
            lottos.add(new Lotto(System.currentTimeMillis()));
        }
    }

    private boolean isPurchaseNumber1000Unit(String rawPurchaseAmount) {
        int purchaseAmount = Integer.parseInt(rawPurchaseAmount);
        if(purchaseAmount < 1000 || purchaseAmount % 1000 != 0)
            return false;

        return true;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getPurchaseAmount() {
        return purchaseNumber * 1000;
    }

    public List<LottoDto> getSortedLottosDto() {
        List<LottoDto> lottosDto = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottosDto.add(LottoDto.getSortedLottoDto(lotto));
        }
        return lottosDto;
    }
}
