package dto;

import domain.Lotto;
import domain.LottoNumber;
import domain.RankPrize;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class ResultDto {

    private static final int RESULT_INIT_NUMBER = 0;
    private static final int INIT_WIN_PRICE = 0;

    private final EnumMap<RankPrize, Integer> resultDto = new EnumMap<>(RankPrize.class);

    {
        Arrays.stream(RankPrize.values())
            .forEach(rank -> resultDto.put(rank, RESULT_INIT_NUMBER));
    }

    public ResultDto(final List<Lotto> issuedLotto, final Lotto lastWinLotto, final LottoNumber bonusNumber) {
        for (Lotto lotto : issuedLotto) {
            final RankPrize rankPrize = RankPrize.of(lotto.compare(lastWinLotto), lotto.isContainNumber(bonusNumber));
            resultDto.put(rankPrize, resultDto.getOrDefault(rankPrize, 0) + 1);
        }
    }

    public EnumMap<RankPrize, Integer> getResultDto() {
        return resultDto;
    }

    public double getPrizeProfit() {
        int totalWinPrice = INIT_WIN_PRICE;
        for (RankPrize rankPrize : resultDto.keySet()) {
            totalWinPrice += rankPrize.getPrice() * resultDto.get(rankPrize);
        }
        return totalWinPrice;
    }
}
