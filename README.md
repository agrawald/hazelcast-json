hazecast-json-smile

Application started with jackson smile but later moved to just use the Jackson ObjectMapper.

Basically the application will gauge the performance of SqlPredicate with custom ValueExtractor
and custom Predicate to search for the entire collection.

Following are my observations

Data Set: {"name": "dummy1"}
Number of records: 1000000 23MB

Time to save: 31229ms
Time to find using Indexing: 6ms [JsonWrapper(rawJson={"name":"dheeraj999999"})]
Time to find without Indexing: 707ms 
