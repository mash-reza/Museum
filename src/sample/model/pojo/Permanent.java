package sample.model.pojo;

import java.util.Date;

public class Permanent {
    private int artObjectFk;
    private Date dateAcquired;
    private Long cost;

    public int getArtObjectFk() {
        return artObjectFk;
    }

    public void setArtObjectFk(int artObjectFk) {
        this.artObjectFk = artObjectFk;
    }

    public Date getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(Date dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
