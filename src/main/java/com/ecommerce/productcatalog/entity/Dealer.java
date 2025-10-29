package com.ecommerce.productcatalog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dealer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dealerId;

    @Column(nullable = false)
    private String dealerName;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid Email Format")
    private String email;

    @Column(nullable = false, unique = true)
    private Long phoneNo;

    @Column(nullable = false)
    private Long alternatePhoneNo;

    @Column(nullable = false)
    private String productCategory;

    @Column(unique = true)
    private Long businessLicenseNo;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false,length = 6)
    private Integer pinCode;

    @Column(nullable = false)
    private String country;

    @CreationTimestamp
    private LocalDateTime addedDate;

}
