package test;

import javax.persistence.Basic;
import javax.persistence.Entity;

@javax.persistence.Table(name = "money", schema = "", catalog = "itis")
@Entity
public class MoneyEntity {
    private int id;

    @javax.persistence.Column(name = "id")
    @javax.persistence.Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @javax.persistence.Column(name = "name")
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private float balance;

    @javax.persistence.Column(name = "balance")
    @Basic
    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoneyEntity that = (MoneyEntity) o;

        if (Float.compare(that.balance, balance) != 0) return false;
        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (balance != +0.0f ? Float.floatToIntBits(balance) : 0);
        return result;
    }
}
