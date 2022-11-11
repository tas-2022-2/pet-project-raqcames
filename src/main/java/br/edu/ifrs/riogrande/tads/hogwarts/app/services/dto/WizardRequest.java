package br.edu.ifrs.riogrande.tads.hogwarts.app.services.dto;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WizardRequest {
    
    @NotNull(message = "Missing wizard name")
    String name;
    
    @NotNull(message = "Missing wizard age")
    Integer age;

    @NotNull(message = "Missing wizard house id")
    Integer houseId;
    
}
