package view.dto;

import java.util.List;
import java.util.Map;
import model.Prize;

public record ResultDTO(
        List<PrizeDTO> prizeDTOs
) {
    public record PrizeDTO(int count, int price, int match){
        public static PrizeDTO from(Prize prize, int count){
            return new PrizeDTO(prize.getCount(), prize.getPrice(), count);
        }
    }
    public static ResultDTO from(Map<Prize, Integer> result){
        return new ResultDTO(result.entrySet()
                .stream()
                .map((entry) -> PrizeDTO.from(entry.getKey(), entry.getValue()))
                .toList());
    }

}
