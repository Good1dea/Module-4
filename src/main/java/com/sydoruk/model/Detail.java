package com.sydoruk.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private Timestamp date;
    private double productionTime;
    private int brokenChips;
    private int usedFuel;
    private int extractedFuel;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = new Timestamp(System.currentTimeMillis());
        }
    }
}