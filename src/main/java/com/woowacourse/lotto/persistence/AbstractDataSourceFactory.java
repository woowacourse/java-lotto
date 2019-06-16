package com.woowacourse.lotto.persistence;

import javax.sql.DataSource;

public interface AbstractDataSourceFactory {
    DataSource createDataSource();
}
