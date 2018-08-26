package czachor.jakub.rooms.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NICKNAME", unique = true, updatable = false, nullable = false)
    private String nickname;

    @Column(name = "POINTS")
    private Integer points;
}
