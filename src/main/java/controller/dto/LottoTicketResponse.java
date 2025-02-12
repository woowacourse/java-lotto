package controller.dto;

import java.util.List;

public record LottoTicketResponse(
        List<Integer> numbers
) {

    public static LottoTicketResponse from(List<Integer> numbers) {
        return new LottoTicketResponse(numbers);
    }

}
