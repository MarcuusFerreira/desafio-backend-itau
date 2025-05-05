package com.desafio.itau.statistics.domain.entities.statistics;

import java.math.BigDecimal;

public class Statistics {

    private Integer count;
    private BigDecimal sum;
    private BigDecimal avg;
    private BigDecimal min;
    private BigDecimal max;

    public Statistics(Integer count, BigDecimal sum, BigDecimal avg, BigDecimal min, BigDecimal max) {
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

}
