package dto;

import java.util.List;

public record LottosResponse(
        List<LottoNumbersResponse> lottos
) {
}
