package library.Managemnt.library.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
@Entity
@Data
public class BookIssue {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
 private int bookIssueId;
	@ManyToOne
	@JoinColumn(name = "bookId",nullable=false)
 private Book book;
 private LocalDate issueDate;
 private LocalDate returnDate;
 private String status;
 @ManyToOne
 @JoinColumn(name="userId", nullable=false)
 private User user;
 @Transient
 private String dueDays;
}
