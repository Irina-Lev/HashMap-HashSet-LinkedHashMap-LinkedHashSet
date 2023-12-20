package examination;

import java.util.LinkedHashMap;
import java.util.Map;

public class CashedExamination implements CashExamination {

    Map<String, Double> cashed = new LRUCache<>(2);
    private final InMemoryExamination inMemoryExamination;
    public CashedExamination(InMemoryExamination inMemoryExamination) {
        this.inMemoryExamination = inMemoryExamination;
    }


    @Override
    public Double averageGrade(String subject) {
        return cashed.computeIfAbsent(subject, inMemoryExamination::averageGrade);
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