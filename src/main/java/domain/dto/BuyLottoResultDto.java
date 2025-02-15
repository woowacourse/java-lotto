package domain.dto;

import java.util.List;

public record BuyLottoResultDto(List<List<Integer>> buyLottos, int amount) {
}
