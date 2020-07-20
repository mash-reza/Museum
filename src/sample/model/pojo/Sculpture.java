package sample.model.pojo;

public class Sculpture {
    private int artObjectIdFk;
    private float height;
    private float weight;
    private String type;

    public int getArtObjectIdFk() {
        return artObjectIdFk;
    }

    public void setArtObjectIdFk(int artObjectIdFk) {
        this.artObjectIdFk = artObjectIdFk;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
