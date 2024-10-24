package az.edu.orient.lms.entity;


import az.edu.orient.lms.config.JsonNodeType;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "registration_requests")
@TypeDef(name = "jsonb", typeClass = JsonNodeType.class)
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String email;
    private String mobile;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private char gender;
    private String pin;
    private String address;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode educationData;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode careerData;

    private String requestToken;
    private int requestStatus;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
