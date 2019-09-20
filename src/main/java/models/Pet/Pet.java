package models.Pet;

import java.util.ArrayList;
import java.util.Objects;

public class Pet {

    private long id;
    private String name;
    private String status;

    private ArrayList<String> photoUrls = new ArrayList<>();
    private ArrayList<Tags> tags = new ArrayList<>();

    private Category categoryObject;

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return categoryObject;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCategory(Category categoryObject) {
        this.categoryObject = categoryObject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhotoUrls(ArrayList<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void setTags(ArrayList<Tags> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getPhotoUrls() {
        return photoUrls;
    }

    public ArrayList<Tags> getTags() {
        return tags;
    }

    public static Pet createDefaultPet() {
        ArrayList<String> urlPhoto = new ArrayList<>();
        ArrayList<Tags> tags = new ArrayList<>();
        urlPhoto.add("https://pbs.twimg.com/profile_images/429645944754159616/jzY41c_k.png");
        tags.add(new Tags());
        Pet pet = new Pet();
        pet.setId((long)(Math.random()*1000));
        pet.setName("Vincent");
        pet.setStatus("available");
        pet.setPhotoUrls(urlPhoto);
        pet.setTags(tags);
        Category category = new Category();
        category.setId((long) (Math.random()*1000));
        category.setName("category");
        pet.setCategory(category);
        return pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return getId() == pet.getId() &&
                Objects.equals(getName(), pet.getName()) &&
                Objects.equals(getStatus(), pet.getStatus()) &&
                Objects.equals(getPhotoUrls(), pet.getPhotoUrls()) &&
                Objects.equals(getTags(), pet.getTags()) &&
                Objects.equals(categoryObject, pet.categoryObject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStatus(), getPhotoUrls(), getTags(), categoryObject);
    }

}

