/**
 * 
 */
package hulva.luva.demo.restExpressDemo;

import java.util.UUID;

import org.restexpress.Request;
import org.restexpress.Response;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2017年2月7日
 *
 */
public class MyResource {

  /**
   * GET --> read(Request, Response)<br>
   * PUT --> update(Request, Response)<br>
   * POST --> create(Request, Response)<br>
   * DELETE --> delete(Request, Response)<br>
   */

  public String read(Request request, Response response) {
    // throw new RuntimeException("message goes here");
    return "Got it!";
  }

  public Model create(Request request, Response response) {
    return new Model("todd", "http://www.toddfredrich.com/");
  }

  public class Model {
    private UUID id = UUID.randomUUID();
    private String name;
    private String href;

    public Model(String name, String href) {
      super();
      this.name = name;
      this.href = href;
    }

    public UUID getId() {
      return id;
    }

    public void setId(UUID id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getHref() {
      return href;
    }

    public void setHref(String href) {
      this.href = href;
    }
  }
}
