package lotto.dto;

import lotto.domain.Ticket;
import lotto.domain.UserLottos;

import java.util.List;
import java.util.stream.Collectors;

public class UserLottoDto {
    private int lottoMoney;
    private int manualCount;
    private List<List<Integer>> numbers;

    public UserLottoDto(UserLottos userLottos) {
        this.numbers = userLottos.tickets().stream().map(Ticket::ticketNumbers).collect(Collectors.toList());
    }

    public UserLottoDto() {
    }

    public int getLottoMoney() {
        return lottoMoney;
    }

    public void setLottoMoney(int lottoMoney) {
        this.lottoMoney = lottoMoney;
    }

    public int getManualCount() {
        return manualCount;
    }

    public void setManualCount(int manualCount) {
        this.manualCount = manualCount;
    }

    public List<List<Integer>> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<List<Integer>> numbers) {
        this.numbers = numbers;
    }
}
