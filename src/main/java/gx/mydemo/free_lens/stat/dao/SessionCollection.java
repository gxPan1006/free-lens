package gx.mydemo.free_lens.stat.dao;

import jakarta.persistence.*;

import java.sql.Timestamp;
import lombok.Data;

@Entity
@Table(name = "session_collection")
@Data
public class SessionCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "collection_name", nullable = false, length = 50)
    private String collectionName;

    @Column(name = "para", columnDefinition = "json")
    private String para;

    @Column(name = "created_at", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;
}