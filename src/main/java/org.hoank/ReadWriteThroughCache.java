package org.hoank;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * Created by hoank92 on Sep, 2019
 */
public class ReadWriteThroughCache {

    private final HazelcastInstance instance;

    public ReadWriteThroughCache(HazelcastInstance instance) {
        this.instance = instance;
    }

    public void run() {

        IMap<String, Supplement> supplements = instance.getMap("supplements");
        System.out.println(supplements.size());

        supplements.set("1", new Supplement("bcaa", 10));
        supplements.set("2", new Supplement("protein", 100));
        supplements.set("3", new Supplement("glucosamine", 200));

        System.out.println(supplements.size());

        supplements.evictAll();

        System.out.println(supplements.size());

        supplements.loadAll(true);

        supplements.keySet().forEach(res -> {
            System.out.println(supplements.get(res));
        });
    }
}