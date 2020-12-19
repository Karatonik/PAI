package pl.mateusz.kalksztejn.STM.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mateusz.kalksztejn.STM.models.enums.Status;
import pl.mateusz.kalksztejn.STM.models.enums.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String title;
    private String description;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateAdded;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @ManyToOne
    User user;


    public Task(String title, String description, LocalDateTime dateAdded, Type type, Status status, User user) {
        this.title = title;
        this.description = description;
        this.dateAdded = dateAdded;
        this.type = type;
        this.status = status;
        this.user=user;
    }
}
