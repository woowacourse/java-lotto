package lotto.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PaymentInfoTest {
    private PaymentInfoDao paymentInfoDao;

    @BeforeEach
    void setUp() {
        paymentInfoDao = PaymentInfoDao.getInstance();
    }

    @Test
    void insertUser() throws Exception {
        String name = "tester";
        assertThat(paymentInfoDao.insertUser(name)).isEqualTo(1);
    }
}
