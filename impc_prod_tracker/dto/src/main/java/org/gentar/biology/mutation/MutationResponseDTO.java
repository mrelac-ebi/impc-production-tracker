package org.gentar.biology.mutation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import org.gentar.biology.gene.GeneDTO;
import org.gentar.biology.gene.GeneResponseDTO;
import org.gentar.biology.outcome.OutcomeResponseDTO;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Relation(collectionRelation = "mutations")
@Data
public class MutationResponseDTO extends RepresentationModel<MutationResponseDTO>
{
    @JsonIgnore
    private Long id;
    private String min;
    private String mgiAlleleId;
    private Long imitsAllele;
    private Boolean mgiAlleleSymbolWithoutImpcAbbreviation;

    @JsonProperty("genes")
    private List<GeneResponseDTO> geneDTOS;
    @JsonUnwrapped
    private MutationCommonDTO mutationCommonDTO;
}
