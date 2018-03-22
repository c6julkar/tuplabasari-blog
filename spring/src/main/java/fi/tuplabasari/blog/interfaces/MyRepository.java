package fi.tuplabasari.blog.interfaces;

public interface MyRepository<T, ID> {

    T saveEntry(T entry);

    void deleteEntry(ID id);

    Iterable<T> getAll();

    T getOne(ID id);
}
