package com.fredrikpedersen.obligatoryassignment.web.models.v1;

import lombok.*;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 20:05
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO implements DTO {

    private Long id;
    private String url;
    private String movie;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int quantity;
}
