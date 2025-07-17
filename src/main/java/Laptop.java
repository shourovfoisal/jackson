import com.fasterxml.jackson.annotation.JsonProperty;

public class Laptop {
    private String brand;
    private String model;
    @JsonProperty(value = "retailPrice")
    private Long price;   // doesn't need a getter and setter as it's already annotated

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
