package com.desafio.itau.statistics.domain.entities.transactions;

import java.time.OffsetDateTime;
import java.util.Objects;

public final class TransactionKey implements Comparable<TransactionKey> {

    private final OffsetDateTime datetime;
    private final long sequence;

    public TransactionKey(OffsetDateTime timestamp, long sequence) {
        this.datetime = Objects.requireNonNull(timestamp);
        this.sequence = sequence;
    }

    @Override
    public int compareTo(TransactionKey other) {
        int compare = this.datetime.compareTo(other.datetime);
        return compare != 0 ? compare : Long.compare(this.sequence, other.sequence);
    }

    public OffsetDateTime getDatetime() {
        return datetime;
    }

    public long getSequence() {
        return sequence;
    }
}
