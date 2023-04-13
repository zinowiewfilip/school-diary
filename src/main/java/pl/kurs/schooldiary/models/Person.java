package pl.kurs.schooldiary.models;

import pl.kurs.schooldiary.services.Identificationable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public abstract class Person implements Serializable, Identificationable {

    @Column(nullable = false)
    protected String firstName;

    @Column(nullable = false)
    protected String lastName;

    @Column(unique = true, nullable = false)
    protected String pesel;

    @Column(nullable = false)
    protected LocalDate birthDate;

    public Person() {
    }

    public Person(String firstName, String lastName, String pesel, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(pesel, person.pesel) && Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, pesel, birthDate);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " : " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", birthDate=" + birthDate;
    }

}
