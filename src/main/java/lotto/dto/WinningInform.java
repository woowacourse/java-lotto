package lotto.dto;

import lotto.domain.Lotto;

public record WinningInform(Lotto matchLotto, int bonusNumber) {
}
