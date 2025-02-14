package dto;

import java.util.List;

public record LottoResultsResponse(
        List<LottoResultDetailResponse> detailResponses
) {
}
