/**
 * 
 */
package hulva.luva.learn.corejava.immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

/**
 * @ClassName
 * @Description create a unmodifiable List out of an existing ArrayList using
 *              either the core JDK, Google Guava or Apache Commons Collections.
 * @author Hulva Luva.H
 * @date 2016年11月29日
 *
 */
public class MyImmutableList {

	// Collections.unmodifiableList(list)
	@Test(expected = UnsupportedOperationException.class)
	public void givenUsingTheJdk_whenUnmodifiableListIsCreated_thenNotModifiable() {
		List<String> list = new ArrayList<String>(Arrays.asList("one", "two",
				"three"));
		List<String> unmodifiableList = Collections.unmodifiableList(list);
		unmodifiableList.add("four");
	}

	// ImmutableList.copyOf(list);
	@Test(expected = UnsupportedOperationException.class)
	public void givenUsingGuava_whenUnmodifiableListIsCreated_thenNotModifiable() {
		List<String> list = new ArrayList<String>(Arrays.asList("one", "two",
				"three"));
		List<String> unmodifiableList = ImmutableList.copyOf(list);
		unmodifiableList.add("four");
	}

	@Test(expected = UnsupportedOperationException.class)
	public void givenUsingGuavaBuilder_whenUnmodifiableListIsCreated_thenNoLongerModifiable() {
		List<String> list = new ArrayList<String>(Arrays.asList("one", "two",
				"three"));
		@SuppressWarnings("unused")
		ImmutableList<Object> unmodifiableList = ImmutableList.builder()
				.addAll(list).build();
//		unmodifiableList.add("four"); Unsupported operation
	}

	// ListUtils.unmodifiableList(list);
	@Test(expected = UnsupportedOperationException.class)
	public void givenUsingCommonsCollections_whenUnmodifiableListIsCreated_thenNoLongerModifiable() {
		List<String> list = new ArrayList<String>(Arrays.asList("one", "two",
				"three"));
		List<String> unmodifiableList = ListUtils.unmodifiableList(list);
		unmodifiableList.add("four");
	}
}
