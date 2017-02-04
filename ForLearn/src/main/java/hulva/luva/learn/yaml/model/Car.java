/**
 * 
 */
package hulva.luva.learn.yaml.model;

import java.util.Date;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年1月26日
 *
 */
public class Car {

  private String brand;
  private Date date;
  private Wheel wheel;

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Wheel getWheel() {
    return wheel;
  }

  public void setWheel(Wheel wheel) {
    this.wheel = wheel;
  }

  public Car() {
    super();
  }

  @Override
  public String toString() {
    return "Car [brand=" + brand + ", date=" + date + ", wheel=" + wheel + "]";
  }

}
