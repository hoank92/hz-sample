package com.hz.infra.mysql.helper;

/**
 * Created by hoank92 on Jul, 2020
 */

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;
import java.io.File;
import java.util.Properties;

public class JdbiHelper {

    public static Jdbi createJdbi(DataSource dataSource) {
        var jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    public static Jdbi createJdbiFromProperties(Properties properties) {
        return createJdbi(SqlDataSourceHelper.createFromProperties(properties));
    }

    public static Jdbi createJdbiFromProperties(File propertiesFile) {
        return createJdbi(SqlDataSourceHelper.createFromProperties(propertiesFile));
    }

    public static Jdbi createJdbiFromProperties(String propertiesFilePath) {
        return createJdbi(SqlDataSourceHelper.createFromProperties(propertiesFilePath));
    }
}
