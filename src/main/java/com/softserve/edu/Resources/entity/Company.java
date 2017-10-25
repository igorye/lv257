package com.softserve.edu.Resources.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "company")
/**
 * Represents Company entity as a resource owner
 */
public class Company extends Owner {

    @Column(name = "full_name")
    @JsonProperty("full_name")
    @NotEmpty
    private String fullName;

    @Column(name = "short_name")
    @JsonProperty("short_name")
    @NotEmpty
    private String shortName;

    @Column(name = "organization_form")
    @JsonProperty("organization_form")
    @NotEmpty
    private String organizationForm;

    @Column(name = "edrpo_number")
    @JsonProperty("edrpo_number")
    @Pattern(regexp="[\\d]{8}", message = "Should be 8 digits. E.g., 87963214")
    private String edrpoNumber;

    @Column(name = "CEO")
    @JsonProperty("ceo")
    @NotEmpty
    private String ceo;

    public Company() {
    }

    public Company setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Company setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public Company setOrganizationForm(String organizationForm) {
        this.organizationForm = organizationForm;
        return this;
    }

    public Company setCeo(String ceo) {
        this.ceo = ceo;
        return this;
    }

    public Company setEdrpoNumber(String edrpoNumber) {
        this.edrpoNumber = edrpoNumber;
        return this;
    }

    public String getEdrpoNumber() {
        return edrpoNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getOrganizationForm() {
        return organizationForm;
    }

    public String getCeo() {
        return ceo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        if (!super.equals(o)) return false;

        Company company = (Company) o;

        if (!fullName.equals(company.fullName)) return false;
        if (!shortName.equals(company.shortName)) return false;
        if (!organizationForm.equals(company.organizationForm)) return false;
        if (!edrpoNumber.equals(company.edrpoNumber)) return false;
        return ceo.equals(company.ceo);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + shortName.hashCode();
        result = 31 * result + organizationForm.hashCode();
        result = 31 * result + edrpoNumber.hashCode();
        result = 31 * result + ceo.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", organizationForm='" + organizationForm + '\'' +
                ", CEO='" + ceo + '\'' +
                "} " + super.toString();
    }

    @Override
    public String ownerType() {
        return "Company";
    }

    @Override
    public String customToString() {
        return organizationForm + " " +
                fullName + " " +
                shortName + ", " +
                edrpoNumber + ", " +
                ceo + ".";
    }
}