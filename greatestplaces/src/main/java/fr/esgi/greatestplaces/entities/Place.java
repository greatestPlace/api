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
	
    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = true, length = 45)
    private String address;

    @Column(nullable = true, length = 45)
    private String city;

    @Column(nullable = true, length = 45)
    private String country;

    @Column(nullable = true, length = 45)
    private String zipCode;

    @Column(nullable = true, length = 45)
    private String description;

    @Column(nullable = true, length = 45)
    private String image;

    @Column(nullable = false, length = 45)
    private String latitude;

    @Column(nullable = false, length = 45)
    private String longitude;

    @Column(nullable = false, length = 45)
    private String cattrue;

    @Column(nullable = false, length = 45)
    private Long userId;

    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date")
	private Date creationDate;

    public void  setUserId(Long userId) {
        this.userId = userId;
    }
}