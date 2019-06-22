package lotto.dto;

import lotto.domain.Lotto;

import java.util.List;

public class LottoDto {
    private final RoundDto roundDto;
    private final List<Lotto> userLotto;
    private final String[] numbers;

    public LottoDto(RoundDto roundDto, List<Lotto> userLotto, String[] numbers) {
        this.roundDto = roundDto;
        this.userLotto = userLotto;
        this.numbers = numbers;
    }

    public RoundDto getRoundDto() {
        return roundDto;
    }

    public List<Lotto> getUserLotto() {
        return userLotto;
    }

    public String[] getNumbers() {
        return numbers;
    }
}
