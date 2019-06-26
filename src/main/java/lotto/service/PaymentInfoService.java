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

    public int insertPaymentInfoAndReturnKeyValue(int inputPayment, int countOfManualLotto, String name) throws SQLDataException {
        Payment payment = new Payment(inputPayment);
        CountOfLotto countOfLotto = new CountOfLotto(payment, countOfManualLotto);

        PaymentInfoDto paymentInfoDto = createPaymentInfoDTO(payment, countOfLotto, name);
        return PAYMENT_INFO_DAO.insertPayment(paymentInfoDto);
    }

    private static PaymentInfoDto createPaymentInfoDTO(Payment payment, CountOfLotto countOfLotto, String name) {
        PaymentInfoDto paymentInfoDto = new PaymentInfoDto();
        paymentInfoDto.setPayment(payment.getPayment());
        paymentInfoDto.setManual(countOfLotto.getCountOfManualLotto());
        paymentInfoDto.setAuto(countOfLotto.getCountOfRandomLotto());
        paymentInfoDto.setName(name);
        return paymentInfoDto;
    }
}
