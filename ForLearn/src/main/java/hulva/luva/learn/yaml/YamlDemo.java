/**
 * 
 */
package hulva.luva.learn.yaml;

import hulva.luva.learn.yaml.model.Car;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年1月26日
 *
 */
public class YamlDemo {

  public static void main(String[] args) throws FileNotFoundException {
    /*
     * InputStream is = new FileInputStream(new File("src/main/resources/yamlDemo.yaml")); Yaml yaml
     * = new Yaml(); for (Object data : yaml.loadAll(is)) { System.out.println(data); }
     */

    InputStream is = new FileInputStream(new File("src/main/resources/car.yaml"));
    Yaml yaml = new Yaml();
    Car car = (Car) yaml.load(is);
    System.out.println(car);
    System.out.println(car.getBrand());
    System.out.println(car.getDate().getTime());
    System.out.println(car.getWheel());
    System.out.println(car.getWheel().getBrand());
    System.out.println(car.getWheel().getLevel());
    System.out.println(car.getWheel().getPrice());
  }

}
