package io.relay.model.api;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.relay.util.DateToLongSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditNoteDto implements OnCreate, OnUpdate {

    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    private UUID id;

    @JsonProperty(value = "creditNumber", required = true)
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private String number;

    @JsonProperty(required = true)
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    @JsonFormat(shape = STRING)
    private BigDecimal value;

    @NotNull(groups = OnUpdate.class)
    @Null(groups = OnCreate.class)
    @JsonFormat(shape = STRING)
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createdAt;

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

    public Date getCreatedAt() {
        return new Date(createdAt.getTime());
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt != null ? new Date(createdAt.getTime()) : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreditNoteDto)) {
            return false;
        }
        CreditNoteDto that = (CreditNoteDto) o;
        return id.equals(that.id) && number.equals(that.number) && value.equals(that.value) && createdAt.equals(that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, value, createdAt);
    }
}
