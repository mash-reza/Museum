package sample.model.dto;

import sample.model.pojo.ArtObject;
import sample.model.pojo.Artist;

import java.util.Date;

public class ArtObjectArtistView {
    private String name;
    private Date dateBorn;
    private Date dateDied;
    private String epoch;
    private String title;
    private Date year;
    private String description;
    private String country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(Date dateBorn) {
        this.dateBorn = dateBorn;
    }

    public Date getDateDied() {
        return dateDied;
    }

    public void setDateDied(Date dateDied) {
        this.dateDied = dateDied;
    }

    public String getEpoch() {
        return epoch;
    }

    public void setEpoch(String epoch) {
        this.epoch = epoch;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
