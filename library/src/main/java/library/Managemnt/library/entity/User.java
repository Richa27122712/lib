package library.Managemnt.library.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {
 @Id
 
private int userId;
 private String userName;
 
}
