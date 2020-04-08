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
package org.gentar.biology.status;

import lombok.*;
import org.gentar.BaseEntity;
import org.gentar.audit.diff.IgnoreForAuditingChanges;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@AllArgsConstructor
@Data
@Entity
public class Status extends BaseEntity
{
    @Id
    @SequenceGenerator(name = "statusSeq", sequenceName = "STATUS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statusSeq")
    private Long id;

    @NotNull
    private String name;

    private String description;

    @IgnoreForAuditingChanges
    @NotNull
    @Column(columnDefinition = "bigint default 0")
    private Integer ordering;
}
