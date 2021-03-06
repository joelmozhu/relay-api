package io.relay.model.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(unique = true)
    @NotNull(message = "invoice number is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9\\-]+$", message = "contains alphanumeric values")
    private String number;

    @Column(name = "amount")
    @NotNull(message = "invoice amount is mandatory")
    private BigDecimal value;

    @Column(updatable = false)
    @CreatedDate
    private Instant createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id)
            && Objects.equals(number, invoice.number)
            && Objects.equals(value, invoice.value)
            && Objects.equals(createdAt, invoice.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, value, createdAt);
    }
}
