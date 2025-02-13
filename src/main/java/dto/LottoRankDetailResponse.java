package dto;

public record LottoRankDetailResponse(
        int matchNumber,
        boolean isBonusNumber,
        int price
) {
}