/**
 * 
 */
package hulva.luva.learn.corejava.aboutlinkedlist;

import java.util.LinkedList;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月29日
 *
 *       LinkedList is a doubly-linked list implementation of the List and Deque
 *       interfaces. It implements all optional list operations and permits all
 *       elements (including null).
 */
public class HelloLinkedList {
	/***
	 * Features
	 * 
	 * Below you can find the most important properties of the LinkedList:
	 * 
	 * 1. Operations that index into the list will traverse the list from the
	 * beginning or the end, whichever is closer to the specified index
	 * 
	 * 2. It is not synchronized
	 * 
	 * 3. Its Iterator and ListIterator iterators are fail-fast (which means
	 * that after the iterator’s creation, if the list is modified, a
	 * ConcurrentModificationException will be thrown)
	 * 
	 * 4. Every element is a node, which keeps a reference to the next and
	 * previous ones
	 * 
	 * 5. It maintains insertion order
	 * 
	 * Although LinkedList is not synchronized, we can retrieve a synchronized
	 * version of it by calling the Collections.synchronizedList method, like:
	 * 
	 * <code>List list = Collections.synchronizedList(new LinkedList(...));</code>
	 * 
	 * 虽然和 ArrayList一样，都是实现了List接口，但显然他们是不同的：
	 * 
	 * 数据结构：ArrayList是一个基于索引的数组，提供对其元素的随机访问，性能为O(1);LinkedList将其数据存储为元素列表，
	 * 每个元素都链接到其previous和next元素。在这种情况下，对元素的搜索操作具有等于O（n）的执行时间.
	 * 
	 * 操作：元素的插入，添加和删除操作在LinkedList中更快，因为当元素添加到集合内的某个任意位置时，不需要调整数组或更新索引，
	 * 只有周围元素中的引用会改变。
	 * 
	 * 内存使用：LinkedList比ArrayList消耗更多的内存，因为LinkedList中的每个节点都存储两个引用，一个用于其上一个元素，
	 * 另一个用于其下一个元素，而ArrayList仅保存数据及其索引。
	 */

	public static void main(String[] args) {
		LinkedList<Object> linkedList = new LinkedList<>();
		// Adding Element
		linkedList.add("hello");
		linkedList.add("world");
		linkedList.add("hahaha");
		System.out.println(linkedList);
		// Removing Element
		System.out.println(linkedList.poll()); // 获取并删除第一个元素
		System.out.println(linkedList.pop()); // 删除并返回该列表的第一个元素
		linkedList.removeLast();
		System.out.println(linkedList);
	}
}
