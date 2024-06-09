package yuriy.spring.news_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(schema = "news_schema", name = "t_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_first_name", nullable = false)
    private String firstName;

    @Column(name = "c_last_name", nullable = false)
    private String lastName;

    @Column(name = "c_middle_name", nullable = false)
    private String middleName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "c_role", nullable = false)
    private Role role;
}
