package lotto.dto;

import lotto.domain.WinningLotto;

import java.util.stream.Collectors;

public class WinningLottoDto {
    private String round;
    private String numbers;
    private String bonusNumber;

    public WinningLottoDto(String round, String numbers, String bonusNumber) {
        this.round = round;
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public WinningLottoDto(String round, WinningLotto winningLotto) {
        this(round,
                winningLotto.getWinningLotto().getLottoNumbers()
                        .stream()
                        .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                        .collect(Collectors.joining(",")),
                String.valueOf(winningLotto.getBonusNumber().getNumber())
        );
    }

    public String getRound() {
        return round;
    }

    public String getNumbers() {
        return numbers;
    }

    public String getBonusNumber() {
        return bonusNumber;
    }
}
