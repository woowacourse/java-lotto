package lotto.model.dto;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lotto.model.prize.Prize;
import lotto.model.prize.PrizeCountMap;

public class PrizeCountDTO {
    private final PrizeDTO prize;
    private final int prizeCount;

    private PrizeCountDTO(PrizeDTO prize, int prizeCount) {
        this.prize = prize;
        this.prizeCount = prizeCount;
    }

    public static List<PrizeCountDTO> from(PrizeCountMap prizeCountMap) {
        return prizeCountMap.getPrizeMap().entrySet().stream()
                .map(PrizeCountDTO::from)
                .collect(Collectors.toList());
    }

    public static PrizeCountDTO from(Entry<Prize, Integer> prizeMapEntry) {
        return new PrizeCountDTO(PrizeDTO.from(prizeMapEntry.getKey()), prizeMapEntry.getValue());
    }

    public PrizeDTO getPrize() {
        return prize;
    }

    public int getPrizeCount() {
        return prizeCount;
    }
}
