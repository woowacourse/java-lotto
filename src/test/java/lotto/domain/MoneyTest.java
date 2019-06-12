package lotto.domain;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author heebg
 * @version 1.0 2019-06-10
 */
class MoneyTest {
    Money money;

    @BeforeEach
    void setUp() {
        money = new Money(3500);
    }

    @AfterEach
    void tearDown() {
        money = null;
    }

    @Test
    void calculateAutoCount() {
        assertThat(money.calculateAutoCount(2)).isEqualTo(1);
    }

    @Test
    void checkManualCount() {
        assertThrows(IllegalArgumentException.class, () -> {
            money.checkManualCount(4);
        });
    }

    @Test
    void calculateRate() {
        Money rateMoney = new Money(8000);
        assertThat(rateMoney.calculateRate(5000)).isEqualTo(0.625f);
    }

    @Test
    void generateLottoBuyCount() {
        assertThat(money.getCount()).isEqualTo(3);
    }

    @Test
    void toStringTest() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("money",3500);
        jsonObject.addProperty("count",3);
        String jsonStr = new Gson().toJson(jsonObject);
        assertThat(new Gson().toJson(money)).isEqualTo(jsonStr);
        System.out.println(new Gson().toJson(money));
    }
}