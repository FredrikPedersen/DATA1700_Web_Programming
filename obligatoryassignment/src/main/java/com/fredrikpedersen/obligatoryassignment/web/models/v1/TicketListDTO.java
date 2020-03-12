package com.fredrikpedersen.obligatoryassignment.web.models.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 21:29
 */

@Getter
@Setter
@AllArgsConstructor
public class TicketListDTO {

    private List<TicketDTO> tickets;
}
