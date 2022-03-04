package lotto.model.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.prize.Prize;
import lotto.model.prize.PrizeCountMap;

public class PrizeCountDTO {
    private final PrizeDTO prize;
    private final Long prizeCount;

    private PrizeCountDTO(PrizeDTO prize, Long prizeCount) {
        this.prize = prize;
        this.prizeCount = prizeCount;
    }

    public static List<PrizeCountDTO> from(PrizeCountMap prizeCountMap) {
        return Arrays.stream(Prize.values())
                .filter(prize -> prize != Prize.NONE)
                .map(prize -> PrizeCountDTO.from(prize, prizeCountMap.getCount(prize)))
                .collect(Collectors.toList());
    }

    private static PrizeCountDTO from(Prize prize, Long count) {
        return new PrizeCountDTO(PrizeDTO.from(prize), count);
    }

    public PrizeDTO getPrize() {
        return prize;
    }

    public Long getPrizeCount() {
        return prizeCount;
    }
}
