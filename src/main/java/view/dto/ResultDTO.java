package view.dto;

import java.util.List;
import java.util.Map;
import model.Prize;

public record ResultDTO(
        List<PrizeDTO> prizeDTOs, double profit
) {
    public record PrizeDTO(int count, int price, int match, boolean isBonus) implements Comparable<PrizeDTO>{
        public static PrizeDTO from(Prize prize, int count){
            return new PrizeDTO(prize.getCount(), prize.getPrice(), count, prize.isBonus());
        }

        @Override
        public int compareTo(PrizeDTO o) {
            int comp = count - o.count;
            System.out.println(comp);
            if (comp == 0) {
                if (isBonus) {
                    return 1;
                }
                return -1;
            }
            return comp;
        }
    }
    public static ResultDTO from(Map<Prize, Integer> result, double profit){
        return new ResultDTO(result.entrySet()
                .stream()
                .map((entry) -> PrizeDTO.from(entry.getKey(), entry.getValue()))
                .toList(), profit);
    }

}
