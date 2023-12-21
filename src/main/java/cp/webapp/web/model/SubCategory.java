package cp.webapp.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class SubCategory {

    @Id
    private String name;

    @ManyToOne
    private Category parentCategory;
}
