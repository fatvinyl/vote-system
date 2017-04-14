package ru.fatvinyl.votesystem;

/**
 *
 * @author Anton Yolgin
 */
public interface HasId {
    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return (getId() == null);
    }
}
