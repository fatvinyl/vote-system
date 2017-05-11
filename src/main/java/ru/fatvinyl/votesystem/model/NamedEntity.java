package ru.fatvinyl.votesystem.model;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Anton Yolgin
 */

@MappedSuperclass
public class NamedEntity extends BaseEntity {

    @NotBlank
    @Column(name = "name", nullable = false)
    protected String name;

    public NamedEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
