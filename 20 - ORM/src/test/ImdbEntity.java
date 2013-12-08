package test;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.math.BigDecimal;

@javax.persistence.Table(name = "imdb", schema = "", catalog = "itis")
@Entity
public class ImdbEntity {
    private String name;

    @javax.persistence.Column(name = "name")
    @javax.persistence.Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int year;

    @javax.persistence.Column(name = "year")
    @Basic
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private BigDecimal rating;

    @javax.persistence.Column(name = "rating")
    @Basic
    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImdbEntity that = (ImdbEntity) o;

        if (year != that.year) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }
}
