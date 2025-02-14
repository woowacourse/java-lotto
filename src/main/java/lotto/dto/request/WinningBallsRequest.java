package lotto.dto.request;

import java.util.List;

public record WinningBallsRequest(
    List<Integer> winningNumbers,
    int bonusNumber
) {
}
