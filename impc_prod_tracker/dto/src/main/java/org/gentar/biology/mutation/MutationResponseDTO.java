package org.gentar.biology.mutation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import org.gentar.biology.gene.GeneDTO;
import org.gentar.biology.outcome.OutcomeResponseDTO;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class MutationResponseDTO extends RepresentationModel<MutationResponseDTO>
{
    @JsonIgnore
    private Long id;
    private String min;
    private String mgiAlleleId;
    private Long imitsAllele;
    private String mgiAlleleSymbol;
    private Boolean mgiAlleleSymbolWithoutImpcAbbreviation;
    private String description;
    private String autoDescription;
    @JsonProperty("genes")
    private List<GeneDTO> geneDTOS;
    @JsonUnwrapped
    private MutationCommonDTO mutationCommonDTO;
}
