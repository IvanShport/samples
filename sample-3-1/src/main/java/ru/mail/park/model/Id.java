package ru.mail.park.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by Solovyev on 01/11/2016.
 */
@SuppressWarnings({"ClassNamingConvention", "unused"})
public class Id<T> {
    private final long id;

    public Id(long id) {
        this.id = id;
    }

    @JsonValue
    public long getId() {
        return id;
    }

    @SuppressWarnings("StaticMethodNamingConvention")
    public static <T> Id<T> of(long id) {
        return new Id<>(id);
    }

    @Override
    public boolean equals(Object subj) {
        if (this == subj) {
            return true;
        }
        if (subj == null || getClass() != subj.getClass()) {
            return false;
        }
        final Id<?> id1 = (Id<?>) subj;
        return id == id1.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Id{"
                + "id=" + id
                + '}';
    }
}
