package dto;

import domain.Lotto;
import domain.LottoNumber;
import domain.RankPrize;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class ResultDto {
    private static final int INIT_WIN_PRICE = 0;
    //3. 이제 결과를 담을 자료구조를 변수로 선언한다.
    private final EnumMap<RankPrize, Integer> resultDto = new EnumMap<>(RankPrize.class);

    private static final int RESULT_INIT_NUMBER = 0;

    //4. 맵의 자료구조를 결과로 이용할 때, counting안된 key도 출력되려면, 인스턴스 초기화 {}를 사용해줘야한다.
    {
        Arrays.stream(RankPrize.values())
            .forEach( rank -> resultDto.put(rank, RESULT_INIT_NUMBER));
    }

    public ResultDto(final List<Lotto> issuedLotto, final Lotto lastWinLotto, final LottoNumber bonusNumber) {
        //2. 생성자에서부터 결과산출을 시작한다. 특히, 관리변수(자료구조)에 담을 로직을 짠다. -> 결과변수에 생성자에서 계산한결과로 초기화시키면 된다.
        // -> 생성자에서 초기화시킬 변수를 자동생성하고, split 해서 인스턴스변수로 올리자.
        for (Lotto lotto : issuedLotto) {
            final RankPrize rankPrize = RankPrize.of(lotto.compare(lastWinLotto), lotto.isContainNumber(bonusNumber));
            resultDto.put(rankPrize, resultDto.getOrDefault(rankPrize, 0) + 1);
        }
    }

    //4. view에서 했떤 로직을 dto결과값클래스 내부에서 다 해놓고 -> getter로 제공하자.
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
