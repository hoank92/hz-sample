package com.hz.infra.mysql;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by hoank92 on Jul, 2020
 */
public class MySqlStorage{
    @Getter(AccessLevel.PROTECTED)
    private final Jdbi jdbi;

    protected MySqlStorage(@NonNull Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    protected Handle openHandle() {
        return this.jdbi.open();
    }

    protected <T, R> R openDaoAndApply(@NonNull Class<T> cls, @NonNull Function<T, R> fn) {
        try (Handle handle = this.openHandle()) {
            return fn.apply(handle.attach(cls));
        }
    }

    protected <T> void openDaoAndAccept(@NonNull Class<T> cls, @NonNull Consumer<T> consumer) {
        try (Handle handle = this.openHandle()) {
            consumer.accept(handle.attach(cls));
        }
    }
}
