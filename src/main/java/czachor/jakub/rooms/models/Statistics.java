package czachor.jakub.rooms.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STATISTIC")
@Data
public class Statistics implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", updatable = false, nullable = false)
    private String name;

    @Column(name = "STRING_VALUE", updatable = true, nullable = true)
    private String stringValue;

    @Column(name = "INT_VALUE", updatable = true, nullable = true)
    private Integer intValue;
}
