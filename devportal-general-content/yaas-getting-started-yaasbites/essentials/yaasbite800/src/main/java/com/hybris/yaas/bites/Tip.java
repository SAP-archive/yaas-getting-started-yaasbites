package com.hybris.yaas.bites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

import static com.hybris.yaas.bites.StringUtils.restrictLength;
import static com.hybris.yaas.bites.StringUtils.restrictThenLowercase;

@Entity
public class Tip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;  // See JIRA  https://jira.hybris.com/browse/FROG-3800 - the id must be a string instead of a long

    private String tenant;

    private String tip;

    public Tip() {
    }

    public Tip(String id, String tip, String tenant) {
        this.id = id;
        this.tip = restrictLength(tip);
        this.tenant = restrictThenLowercase(tenant);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTip(String tip) {
        this.tip = restrictLength(tip);
    }

    public String getTip() {
        return tip;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = restrictThenLowercase(tenant);
    }

    public String toString() {
        return "Tip[ Id: " + id + " Tip: " + tip + " Tenant: " + tenant + "]\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenant, tip);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tip tip1 = (Tip) o;
        return Objects.equals(id, tip1.id) &&
                Objects.equals(tenant, tip1.tenant) &&
                Objects.equals(tip, tip1.tip);
    }

}
