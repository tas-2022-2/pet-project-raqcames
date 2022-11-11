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
public class RegistrationStatusRequest {
    
    @NotNull(message = "Missing registration status")
    String status;
}
