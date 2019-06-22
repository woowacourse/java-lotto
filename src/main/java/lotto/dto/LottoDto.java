package lotto.dto;

import lotto.domain.Lotto;

import java.util.stream.Collectors;

public class LottoDto {
    private String round;
    private String numbers;

    public LottoDto() {
    }

    public LottoDto(String round, String numbers) {
        this.round = round;
        this.numbers = numbers;
    }

    public LottoDto(String round, Lotto lotto) {
        this(round, lotto.getLottoNumbers()
                .stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getNumber()))
                .collect(Collectors.joining(","))
        );
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }
}
