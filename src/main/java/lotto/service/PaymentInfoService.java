package lotto.service;

import lotto.dao.PaymentInfoDao;
import lotto.domain.paymentinfo.CountOfLotto;
import lotto.domain.paymentinfo.Payment;
import lotto.service.dto.PaymentInfoDto;

import java.sql.SQLDataException;

public class PaymentInfoService {
    private static final PaymentInfoDao PAYMENT_INFO_DAO = PaymentInfoDao.getInstance();

    private PaymentInfoService() {
    }

    private static class PaymentServiceHolder {
        private static final PaymentInfoService INSTANCE = new PaymentInfoService();
    }

    public static PaymentInfoService getInstance() {
        return PaymentServiceHolder.INSTANCE;
    }

    public int insertUser(String userName) throws SQLDataException {
        return PAYMENT_INFO_DAO.insertUser(userName);
    }

    public int insertPaymentInfoAndReturnKeyValue(PaymentInfoDto paymentInfoDto) throws SQLDataException {
        paymentInfoDto.setAuto(calculateCountOfRandomLotto(paymentInfoDto));
        return PAYMENT_INFO_DAO.insertPayment(paymentInfoDto);
    }

    private int calculateCountOfRandomLotto(PaymentInfoDto paymentInfoDto) {
        Payment payment = new Payment(paymentInfoDto.getPayment());
        CountOfLotto countOfLotto = new CountOfLotto(payment, paymentInfoDto.getManual());
        return countOfLotto.getCountOfRandomLotto();
    }

    public int calculateCountOfLotto(PaymentInfoDto paymentInfoDto) {
        return calculateCountOfRandomLotto(paymentInfoDto) + paymentInfoDto.getManual();
    }
}
