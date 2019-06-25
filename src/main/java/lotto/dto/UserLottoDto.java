package lotto.dto;

import lotto.domain.Ticket;
import lotto.domain.UserLottos;

import java.util.List;
import java.util.stream.Collectors;

public class UserLottoDto {
    private List<List<Integer>> numbers;

    public UserLottoDto(UserLottos userLottos) {
        this.numbers = userLottos.tickets().stream().map(Ticket::ticketNumbers).collect(Collectors.toList());
    }

    public UserLottoDto(List<List<Integer>> numbers) {
        this.numbers = numbers;
    }

    public List<List<Integer>> getNumbers() {
        return numbers;
    }
}
