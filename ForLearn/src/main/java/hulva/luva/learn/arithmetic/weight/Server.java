/**
 * 
 */
package hulva.luva.learn.arithmetic.weight;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年1月14日
 *
 */
public class Server implements Weighter {

  private String host;
  private Double weight;

  public Server(String host, Double weight) {
    super();
    this.host = host;
    this.weight = weight;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  @Override
  public Double getWeight() {
    return this.weight;
  }

  @Override
  public String toString() {
    return "Server [host=" + host + ", weight=" + weight + "]";
  }

}
