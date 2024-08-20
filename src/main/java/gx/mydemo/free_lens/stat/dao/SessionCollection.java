package gx.mydemo.free_lens.stat.dao;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "session_collection")
@Data
public class SessionCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_query", nullable = false, length = 1000)
    private String firstQuery;

    @Column(name = "messages_raw", columnDefinition = "json")
    private String messagesRaw;

    @Column(name = "messages_pure", columnDefinition = "json")
    private String messagesPure;

    @Column(name = "user_msg_hash", length = 255)
    private String userMsgHash;

    @Column(name = "collection_id")
    private Integer collectionId;

    @Column(name = "para", columnDefinition = "json")
    private String para;
}
