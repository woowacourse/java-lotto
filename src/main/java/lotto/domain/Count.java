package lotto.domain;

public class Count {
    private static final int ZERO = 0;

    private int ticketCount;

    public Count(String ticketCount){
        validateIntegerNumberFormat(ticketCount);
        validatePositiveNumber(ticketCount);

        this.ticketCount = Integer.parseInt(ticketCount);
    }

    private void validatePositiveNumber(String input) {
        if (Integer.parseInt(input) < ZERO) {
            throw new IllegalArgumentException("음수입니다. 재입력 해주세요");
        }
    }

    private void validateIntegerNumberFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (RuntimeException e) {
            throw new NumberFormatException("정수만 입력 가능합니다. 재입력 해주세요.");
        }
    }

    public void validateOverTicketCount(Count allTicketCount){
        if(this.ticketCount > allTicketCount.ticketCount){
            throw new IllegalArgumentException("구매 할 수 있는 티켓을 초과했습니다. 재입력 해주세요.");
        }
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void calculateAutoTicketCount(int manualTicketCount){
        this.ticketCount -= manualTicketCount;
    }
}
