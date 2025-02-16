package view.dto;

import java.util.List;
import java.util.Map;
import model.Prize;

public record ResultDTO(
        List<InnerResultDetail> prizeDTOs, double profit
) {
    public record InnerResultDetail(
            int count,
            int price,
            int match,
            boolean isBonus
    ) implements Comparable<InnerResultDetail> {
        public static InnerResultDetail from(Prize prize, int count) {
            return new InnerResultDetail(prize.getCount(), prize.getPrice(), count, prize.isBonus());
        }

        @Override
        public int compareTo(InnerResultDetail o) {
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
                .map(entry -> InnerResultDetail.from(entry.getKey(), entry.getValue()))
                .toList(), profit);
    }
}
