package br.com.pp.simplificado.model.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.pp.simplificado.configuration.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9124750656150408149L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "dt_creation")
    private LocalDateTime dateCreation = LocalDateTime.now();

    public BaseEntity() {
        super();
        this.dateCreation = LocalDateTime.now();
    }

    public String getDateCreationString() {
        if (this.dateCreation == null) {
            return "";
        }
        return this.dateCreation.format(DateTimeFormatter.ofPattern(
        		Constants.FORMAT_PATTERN_DATETIME));
    }
   
    public boolean atInsertMode() {
    	return this.id == null;
    }
}
