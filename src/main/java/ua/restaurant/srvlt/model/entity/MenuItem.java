package ua.restaurant.srvlt.model.entity;


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

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
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

    public static class Builder {
        private MenuItem newItem;

        public Builder() {
            newItem = new MenuItem();
        }

        public Builder id(long id){
            newItem.id = id;
            return this;
        }

        public Builder name(String name){
            newItem.name = name;
            return this;
        }

        public Builder nameUa(String nameUa){
            newItem.nameUA = nameUa;
            return this;
        }

        public Builder weight(long weight){
            newItem.weight = weight;
            return this;
        }

        public Builder price(long price){
            newItem.price = price;
            return this;
        }

        public Builder storageQuantity(long quantity){
            newItem.storageQuantity = quantity;
            return this;
        }

        public MenuItem build(){
            return newItem;
        }

    }
}

