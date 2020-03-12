package com.fredrikpedersen.obligatoryassignment.domain.models;

import lombok.*;

import javax.persistence.*;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 19:53
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ticket implements DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String movie;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int quantity;
}
