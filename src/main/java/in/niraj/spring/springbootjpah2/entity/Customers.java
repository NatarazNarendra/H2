package in.niraj.spring.springbootjpah2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Customersdate")
@Setter
@Getter
@ToString
public class Customers {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String location;
	 //   @CreationTimestamp
		/*
		 * @Column(name="created_At") private Date createdAt;
		 * 
		 * @UpdateTimestamp
		 * 
		 * @Column(name="updated_At") private Date updatedAt;
		 */
		private String name;
		public Customers(Long id, String name, String location) {
			super();
			this.id = id;
			this.name = name;
			this.location = location;
			/*
			 * this.createdAt = createdAt; this.updatedAt = updatedAt;
			 */
		}
		public Customers() {
			 
		}

		
		/*
		 * public Customers(long id, String name, String location) { super();
		 * 
		 * this.id = id; this.name = name; this.location = location;
		 */
		 
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}

		/*
		 * public Date getCreatedAt() { return createdAt; } public void
		 * setCreatedAt(Date createdAt) { this.createdAt = createdAt; } public Date
		 * getUpdatedAt() { return updatedAt; } public void setUpdatedAt(Date updatedAt)
		 * { this.updatedAt = updatedAt; } //@SuppressWarnings("unused")
		 */		
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	    
}
