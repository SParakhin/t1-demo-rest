package ru.t1.test.demorest.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
@Audited
public class UserEntity{

  @Id
  @GeneratedValue(generator = "hibernate-uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid4")
  @Column(name = "id", nullable = false)
  private UUID id;

  private String name;

  @Column(unique = true)
  private String phone;

  @Column(unique = true)
  private String email;

}