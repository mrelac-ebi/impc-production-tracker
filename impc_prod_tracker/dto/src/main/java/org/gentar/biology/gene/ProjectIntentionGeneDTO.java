/*******************************************************************************
 * Copyright 2019 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 *******************************************************************************/
package org.gentar.biology.gene;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.gentar.biology.gene.GeneDTO;
import org.gentar.biology.intention.OrthologDTO;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ProjectIntentionGeneDTO
{
    @JsonProperty("gene")
    private GeneDTO geneDTO;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrthologDTO> bestOrthologs;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<OrthologDTO> allOrthologs;
}
