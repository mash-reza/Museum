package sample.model.pojo;

public class Pinting {
    private int artObjectIdFk;
    private String paintType;
    private String paintingMaterial;

    public int getArtObjectIdFk() {
        return artObjectIdFk;
    }

    public void setArtObjectIdFk(int artObjectIdFk) {
        this.artObjectIdFk = artObjectIdFk;
    }

    public String getPaintType() {
        return paintType;
    }

    public void setPaintType(String paintType) {
        this.paintType = paintType;
    }

    public String getPaintingMaterial() {
        return paintingMaterial;
    }

    public void setPaintingMaterial(String paintingMaterial) {
        this.paintingMaterial = paintingMaterial;
    }
}
