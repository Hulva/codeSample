# Overview
> `WatchService` is JDKs internal service which watches for changes on registered objects. These registered objects are necessarily the instances of Watchable interface. When registering the watchable instance with WatchService, we need to specify the kind of change events we are interested in.

## Type of events
1. ENTRY_CREATE
2. ENTRY_DELETE
3. ENTRY_MODIFY
4. OVERFLOW

> WatchService interface extends Closeable interface, means service can be closed as and when required. Normally, it should be done using JVM provided shut down hooks.