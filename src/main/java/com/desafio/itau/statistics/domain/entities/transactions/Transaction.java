package com.desafio.itau.statistics.domain.entities.transactions;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Transaction {

    private BigDecimal value;

    private OffsetDateTime datetime;

    public Transaction(BigDecimal value, OffsetDateTime datetime) {
        this.value = value;
        this.datetime = datetime;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public OffsetDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(OffsetDateTime datetime) {
        this.datetime = datetime;
    }
}
