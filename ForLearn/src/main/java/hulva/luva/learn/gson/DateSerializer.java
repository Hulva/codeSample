/**
 * 
 */
package hulva.luva.learn.gson;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年12月6日
 *
 */
public class DateSerializer implements JsonSerializer<Date> {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd/MM/yyyy");

	@Override
	public JsonElement serialize(Date date, Type typeOfSrc,
			JsonSerializationContext context) {
		return new JsonPrimitive(dateFormat.format(date));
	}

}
