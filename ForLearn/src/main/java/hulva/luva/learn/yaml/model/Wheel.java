/**
 * 
 */
package hulva.luva.learn.yaml.model;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年1月26日
 *
 */
public class Wheel {

  private String brand;
  private Level level;
  private Double price;

  public Wheel() {}

  @Override
  public String toString() {
    return "Wheel [brand=" + brand + ", level=" + level + ", price=" + price + "]";
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

}
