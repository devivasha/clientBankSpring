package app.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private Double amount;
    private Long destination;

    public TransactionDto(Long id, Double amount, Long destination) {
        this.id = id;
        this.amount = amount;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getDestination() {
        return destination;
    }

    public void setDestination(Long destination) {
        this.destination = destination;
    }
}
