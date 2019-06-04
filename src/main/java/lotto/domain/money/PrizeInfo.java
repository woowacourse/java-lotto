package lotto.domain.money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrizeInfo {
    private Map<Prize, Integer> prizeInfo = new HashMap<>();

    {
        for (Prize prize : Prize.values()) {
            prizeInfo.put(prize, 0);
        }
    }

    public PrizeInfo(List<Prize> prizes) {
        for (Prize prize : prizes) {
            prizeInfo.put(prize, prizeInfo.get(prize) + 1);
        }

    }

    public int get(Prize prize) {
        return prizeInfo.get(prize);
    }
}
