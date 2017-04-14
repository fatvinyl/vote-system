package ru.fatvinyl.votesystem.to;


import ru.fatvinyl.votesystem.HasId;

/**
 * @author Anton Yolgin
 *
 */
abstract public class BaseTo implements HasId {
    protected Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
