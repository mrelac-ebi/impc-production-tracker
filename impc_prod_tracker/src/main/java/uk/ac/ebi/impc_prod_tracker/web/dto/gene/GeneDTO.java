package uk.ac.ebi.impc_prod_tracker.web.dto.gene;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GeneDTO
{
    @JsonProperty("accessionId")
    private String accId;
    private String symbol;
    private String speciesName;
    private String externalLink;
}
