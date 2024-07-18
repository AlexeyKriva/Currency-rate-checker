package org.vadarod.currencyratechecker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "currency")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @Id
    private long id;
    private LocalDateTime date;
    private String abbreviation;
    private long scale;
    private String name;
    private float officialRate;
}
