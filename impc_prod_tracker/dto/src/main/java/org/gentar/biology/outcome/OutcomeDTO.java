package org.gentar.biology.outcome;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.gentar.biology.mutation.MutationDTO;
import org.gentar.biology.colony.ColonyDTO;
import org.gentar.biology.specimen.SpecimenDTO;
import org.springframework.hateoas.server.core.Relation;
import java.util.List;

@Data
@RequiredArgsConstructor
@Relation(collectionRelation = "outcomes")
public class OutcomeDTO
{
    private Long id;

    @JsonIgnore
    private Long attemptId;

    private String pin;

    private String tpo;

    private String outcomeTypeName;

    @JsonProperty("mutations")
    private List<MutationDTO> mutationDTOS;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("colony")
    private ColonyDTO colonyDTO;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("specimen")
    private SpecimenDTO specimenDTO;
}
