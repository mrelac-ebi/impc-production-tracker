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
package uk.ac.ebi.impc_prod_tracker.data.biology.crispr_attempt.reagent;

import lombok.*;
import uk.ac.ebi.impc_prod_tracker.data.BaseEntity;

import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Data
@Entity
public class Reagent extends BaseEntity
{
    @Id
    @SequenceGenerator(name = "reagentSeq", sequenceName = "REAGENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reagentSeq")
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

}