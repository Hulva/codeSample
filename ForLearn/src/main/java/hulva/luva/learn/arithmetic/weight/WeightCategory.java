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
public class WeightCategory {
  private String category;
  private Integer weight;

  public WeightCategory() {
    super();
  }

  public WeightCategory(String category, Integer weight) {
    super();
    this.setCategory(category);
    this.setWeight(weight);
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "WeightCategory [category=" + category + ", weight=" + weight + "]";
  }


}
