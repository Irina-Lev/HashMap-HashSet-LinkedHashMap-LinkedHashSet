package examination;

import java.util.LinkedHashMap;
import java.util.Map;

public class CaсhedExamination implements CashExamination {

    Map<String, Double> cashed = new LRUCache<>(2);
    private final InMemoryExamination inMemoryExamination;
    public CaсhedExamination(InMemoryExamination inMemoryExamination) {
        this.inMemoryExamination = inMemoryExamination;
    }


    @Override
    public Double averageGrade(String subject) {
        if (cashed.containsKey(subject)){
            return cashed.get(subject);
        }else {
            double average = inMemoryExamination.averageGrade(subject);
            cashed.put(subject, average);
            return  average;
        }
    }
}

class LRUCache<KEY, VALUE> extends LinkedHashMap<KEY, VALUE> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<KEY, VALUE> eldest) {
        return size() > capacity;
    }
}