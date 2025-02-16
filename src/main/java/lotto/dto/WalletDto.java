package lotto.dto;


import java.util.List;
import lotto.domain.Wallet;

public record WalletDto(List<LottoDto> lottoDtos) {

    public static WalletDto from(Wallet wallet) {
        List<LottoDto> dtos = wallet.getLottos().stream()
                .map(LottoDto::from)
                .toList();
        return new WalletDto(dtos);
    }
}
