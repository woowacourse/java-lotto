package view.dto;

import java.util.List;
import java.util.Map;
import model.Prize;

public record ResultDTO(
        List<WinningStatDTO> prizeDTOs, double profit
) {
    public record WinningStatDTO(int count, int price, int match, boolean isBonus) implements
            Comparable<WinningStatDTO> {
        public static WinningStatDTO from(Prize prize, int count) {
            return new WinningStatDTO(prize.getCount(), prize.getPrice(), count, prize.isBonus());
        }

        @Override
        public int compareTo(WinningStatDTO o) {
            int comp = count - o.count;
            if (comp == 0) {
                if (isBonus) {
                    return 1;
                }
                return -1;
            }
            return comp;
        }
    }

    public static ResultDTO from(Map<Prize, Integer> result, double profit) {
        return new ResultDTO(result.entrySet()
                .stream()
                .map((entry) -> WinningStatDTO.from(entry.getKey(), entry.getValue()))
                .toList(), profit);
    }

}
