package com.ashan.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Entity
public class Station extends RepresentationModel<Station> {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String stationId;
    @NotBlank
    @Size(max = 255, message = "Too large, cannot enter")
    private String nimi;
    @NotBlank
    @Size(max = 255, message = "Too large, cannot enter")
    private String namn;
    @NotBlank
    @Size(max = 255, message = "Too large, cannot enter")
    private String name;

    @Size(max = 255, message = "Too large, cannot enter")
    private String osoite;
    @NotBlank
    @Size(max = 255, message = "Too large, cannot enter")
    private String address;
    @NotBlank
    @Size(max = 255, message = "Too large, cannot enter")
    private String city;

    @Size(max = 50, message = "Too large, cannot enter")
    private String stad;

    @Size(max = 50, message = "Too large, cannot enter")
    private String operator;
    private int capacity;
    @Size(max = 50, message = "Too large, cannot enter")
    @Column(name = "coordinate_x")
    private String coordinateX;
    @Size(max = 50, message = "Too large, cannot enter")
    @Column(name = "coordinate_y")
    private String coordinateY;

    public Station() { super();}

    public Station(Long id, String stationId, String nimi, String namn, String name, String osoite, String address,
                   String city, String stad, String operator, int capacity, String coordinateX, String coordinateY) {
        super();
        this.id = id;
        this.stationId = stationId;
        this.nimi = nimi;
        this.namn = namn;
        this.name = name;
        this.osoite = osoite;
        this.address = address;
        this.city = city;
        this.stad = stad;
        this.operator = operator;
        this.capacity = capacity;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOsoite() {
        return osoite;
    }

    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }

    public String getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Station)) return false;
//        Station station = (Station) o;
//        return capacity == station.capacity && Objects.equals(id, station.id) && Objects.equals(stationId, station.stationId) && Objects.equals(nimi, station.nimi) && Objects.equals(namn, station.namn) && Objects.equals(name, station.name) && Objects.equals(osoite, station.osoite) && Objects.equals(address, station.address) && Objects.equals(city, station.city) && Objects.equals(stad, station.stad) && Objects.equals(operator, station.operator) && Objects.equals(coordinateX, station.coordinateX) && Objects.equals(coordinateY, station.coordinateY);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, stationId, nimi, namn, name, osoite, address, city, stad, operator, capacity, coordinateX, coordinateY);
//    }
}
