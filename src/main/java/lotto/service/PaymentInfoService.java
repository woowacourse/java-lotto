package lotto.service;

import lotto.dao.PaymentInfoDao;
import lotto.domain.paymentinfo.CountOfLotto;
import lotto.domain.paymentinfo.Payment;
import lotto.service.dto.PaymentInfoDto;

public class PaymentInfoService {
    private PaymentInfoDao paymentInfoDao = PaymentInfoDao.getInstance();

    private PaymentInfoService() {
    }

    private static class PaymentServiceHolder {
        private static final PaymentInfoService INSTANCE = new PaymentInfoService();
    }

    public static PaymentInfoService getInstance() {
        return PaymentServiceHolder.INSTANCE;
    }

    public int insertUser(String userName) {
        return paymentInfoDao.insertUser(userName);
    }

    public int insertPaymentInfoAndReturnKeyValue(PaymentInfoDto paymentInfoDto) {
        paymentInfoDto.setAuto(calculateCountOfRandomLotto(paymentInfoDto));
        return paymentInfoDao.insertPayment(paymentInfoDto);
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
