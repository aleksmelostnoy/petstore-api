package models;

import java.util.Objects;

public class Order {
    private long id;
    private long petId;
    private int quantity;
    private Status status;
    private boolean complete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public static Order createDefaultOrder() {
        Order order = new Order();
        order.setId((int)(Math.random()*1000));
        order.setPetId(2);
        order.setQuantity(1);
        order.setStatus(Status.delivered);
        order.setComplete(false);
        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId() == order.getId() &&
                getPetId() == order.getPetId() &&
                getQuantity() == order.getQuantity() &&
                isComplete() == order.isComplete() &&
                getStatus() == order.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPetId(), getQuantity(), getStatus(), isComplete());
    }

}