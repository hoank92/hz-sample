package com.hz;

/**
 * Created by hoank92 on Sep, 2019
 */

import com.hazelcast.config.Config;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hz.infra.mysql.MySqlProductStorage;
import com.hz.infra.mysql.MysqlMapStore;
import com.hz.infra.mysql.helper.JdbiHelper;

public class Main {
    public static void main(String[] args) {
        var jdbi = JdbiHelper.createJdbiFromProperties("config/mysql-db.properties");
        var mySqlProductStorage = new MySqlProductStorage(jdbi);

        Config config = new Config();
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig
                .setEnabled(true)
                .setImplementation(new MysqlMapStore(mySqlProductStorage));
        config.getMapConfig("db-backed-map").setMapStoreConfig(mapStoreConfig);

        HazelcastInstance instance1 = Hazelcast.newHazelcastInstance(config);

        IMap<Integer, String> dbBackedMap = instance1.getMap("db-backed-map");


        dbBackedMap.put(1, "hoank1");
        System.out.println(dbBackedMap.get(1));
    }
}