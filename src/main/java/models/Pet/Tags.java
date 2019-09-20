package models.Pet;

import java.util.Objects;

public class Tags {

    private long id;
    private String name;

    public Tags() {
        id = (long) (Math.random()*1000);
        name = "TestTagName1";
    }

    // Getter Methods

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tags tags = (Tags) o;
        return getId() == tags.getId() &&
                Objects.equals(getName(), tags.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
