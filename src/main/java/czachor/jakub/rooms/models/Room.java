package czachor.jakub.rooms.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ROOMS")
@Data
public class Room implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", updatable = false, nullable = false)
    private String name;

    @Column(name = "INFO")
    private String info;

    @Column(name = "KEY", updatable = false, nullable = false)
    private String key;
}
