package dto;

public record LottoResultDetailResponse(
        LottoRankDetailResponse rankDetailResponse,
        int count
) {
}
