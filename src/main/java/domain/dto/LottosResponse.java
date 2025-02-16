package domain.dto;

import java.util.List;

public record LottosResponse(
        List<LottoResponse> lottoResponses
) {
}
