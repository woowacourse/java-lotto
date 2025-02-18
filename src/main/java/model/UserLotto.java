package model;

import dto.LottoDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class UserLotto {
    private static final String PURCHASE_AMOUNT_1000_UNIT_EXCEPTION = "1000원 단위의 금액을 입력해주세요.\n";
    private static final int LOTTO_PRICE = 1000;
    private static final int BONUS_REQUIRED_RANK_NUMBER = 5;

    private final List<Lotto> lottos = new ArrayList<>();

    public UserLotto(NumberGenerator numberGenerator, int purchaseAmount) {
        if (!isPurchaseNumber1000Unit(purchaseAmount)) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_1000_UNIT_EXCEPTION);
        }

        int purchaseNumber = purchaseAmount / LOTTO_PRICE;
        for (int i = 0; i < purchaseNumber; i++) {
            lottos.add(new Lotto(numberGenerator.generateNumbers()));
        }
    }

    private boolean isPurchaseNumber1000Unit(int purchaseAmount) {
        return purchaseAmount >= LOTTO_PRICE && purchaseAmount % LOTTO_PRICE == 0;
    }

    public List<LottoDto> toDto() {
        List<LottoDto> lottosDto = new ArrayList<>();
        for (Lotto lotto : lottos) {
            lottosDto.add(lotto.toDto());
        }
        return lottosDto;
    }

    public void calculateLottoResult(EnumMap<Rank, Integer> ranks, WinningLotto winningLotto) {
        for (Lotto lotto : lottos) {
            Rank rank = calculateRank(lotto, winningLotto);
            ranks.put(rank, ranks.get(rank) + 1);
        }
    }

    private Rank calculateRank(Lotto lotto, WinningLotto winningLotto) {
        int duplicateCount = getDuplicateNumber(lotto, winningLotto);
        if (duplicateCount == BONUS_REQUIRED_RANK_NUMBER && winningLotto.isBonusMatch(lotto)) {
            return Rank.SECOND;
        }

        return Arrays.stream(Rank.values())
                .filter(rank -> rank.getMatchNumber() == duplicateCount)
                .findFirst()
                .orElse(Rank.FAIL);
    }

    private int getDuplicateNumber(Lotto lotto, WinningLotto winningLotto) {
        return lotto.calculateDuplicateNumber(winningLotto.getWinningNumbers());
    }
}
