# `hashCode()` and `equals()`
- `hashCode()`用于获取代表当前对象的一个唯一的整数。当此对象被存储于在某些类似于`HashTable`的数据结构中，这个整数被用于确定bucket location。默认情况下，Object的hashCode（）方法返回和存储对象的内存地址的整数表示。
- `equals()`方法，就像其名称那样，用于简单地验证两个对象的相等性。默认实现只需检查两个对象的对象引用，以验证它们的相等性。