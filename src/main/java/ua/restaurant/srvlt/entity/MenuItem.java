package ua.restaurant.srvlt.entity;


import java.util.Objects;

public class MenuItem {

    private long id;
    private String name;
    private String nameUA;
    private long weight;
    private long price;
    private long storageQuantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getStorageQuantity() {
        return storageQuantity;
    }

    public void setStorageQuantity(long storageQuantity) {
        this.storageQuantity = storageQuantity;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameUA='" + nameUA + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", storageQuantity=" + storageQuantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return id == menuItem.id &&
                weight == menuItem.weight &&
                price == menuItem.price &&
                storageQuantity == menuItem.storageQuantity &&
                name.equals(menuItem.name) &&
                nameUA.equals(menuItem.nameUA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nameUA, weight, price, storageQuantity);
    }

    public static class Builder {
        private MenuItem newItem;

        public Builder() {
            newItem = new MenuItem();
        }

        public Builder id(long id) {
            newItem.id = id;
            return this;
        }

        public Builder name(String name) {
            newItem.name = name;
            return this;
        }

        public Builder nameUa(String nameUa) {
            newItem.nameUA = nameUa;
            return this;
        }

        public Builder weight(long weight) {
            newItem.weight = weight;
            return this;
        }

        public Builder price(long price) {
            newItem.price = price;
            return this;
        }

        public Builder storageQuantity(long quantity) {
            newItem.storageQuantity = quantity;
            return this;
        }

        public MenuItem build() {
            return newItem;
        }

    }
}

