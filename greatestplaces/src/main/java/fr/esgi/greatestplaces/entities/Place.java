package fr.esgi.greatestplaces.entities;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "places")
public class Place {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(name="name", nullable = false, length = 64)
    private String name;

    @Column(name="address",nullable = true, length = 256)
    private String address;

    @Column(name="city",nullable = true, length = 64)
    private String city;

    @Column(name="country",nullable = true, length = 45)
    private String country;

    @Column(name="zip_code",nullable = true, length = 45)
    private String zipCode;

    @Column(name="description",nullable = true, length = 256)
    private String description;

    @Column(name="image",nullable = true, length = 45)
    private String image;

    @Column(name="latitude",nullable = false, length = 45)
    private String latitude;

    @Column(name="longitude",nullable = false, length = 45)
    private String longitude;

    @Column(name = "user_id", nullable = false, length = 45)
    private Long userId;

    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    
}