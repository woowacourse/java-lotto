package lotto.domain.dto;

public class PaymentInfoDTO {
    private int payment;
    private int userId;

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PaymentInfoDTO(int payment, int userId) {
        this.payment = payment;
        this.userId = userId;
    }
}
