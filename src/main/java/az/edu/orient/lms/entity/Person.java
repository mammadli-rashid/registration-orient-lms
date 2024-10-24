package az.edu.orient.lms.entity;

import az.edu.orient.lms.config.JsonNodeType;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
@TypeDef(name = "jsonb", typeClass = JsonNodeType.class)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private Date birthDate;
    private char gender;
    private String email;
    private String mobile;
    private String pin;
    private String address;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode educationData;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private JsonNode careerData;

    @OneToMany(mappedBy = "person")
    private Set<User> users;
}
