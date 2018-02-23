package pro.anuj.citrine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Material implements Serializable {

    @NotNull
    private String formula;
    @NotNull
    private String color;
    @NotNull
    private Double bandGap;
    @JsonIgnore
    private Set<String> processed = new HashSet<>();
}
