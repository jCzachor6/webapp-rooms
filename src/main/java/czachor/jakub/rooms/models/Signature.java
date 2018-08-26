package czachor.jakub.rooms.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SIGNATURES")
@Data
public class Signature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CONTENT", updatable = false, nullable = false)
    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Room room;
}
