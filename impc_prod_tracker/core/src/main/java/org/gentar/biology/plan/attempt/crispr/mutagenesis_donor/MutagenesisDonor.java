package org.gentar.biology.plan.attempt.crispr.mutagenesis_donor;

import lombok.*;
import org.gentar.BaseEntity;
import org.gentar.biology.plan.attempt.crispr.CrisprAttempt;
import org.gentar.biology.plan.attempt.crispr.mutagenesis_donor.preparation_type.PreparationType;
import javax.persistence.*;

@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Data
@Entity
public class MutagenesisDonor extends BaseEntity
{
    @Id
    @SequenceGenerator(name = "mutagenesisDonorSeq", sequenceName = "MUTAGENESIS_DONOR_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mutagenesisDonorSeq")
    private Long id;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "attempt_id")
    private CrisprAttempt crisprAttempt;

    @ManyToOne
    private PreparationType preparationType;

    @Column(columnDefinition = "TEXT")
    private String oligoSequenceFasta;

    private String vectorName;

    @Override
    public String toString()
    {
        String preparationTypeName = preparationType == null ? "Not defined" : preparationType.getName();
        String vectorNameToReport = vectorName == null ? "Not defined" : vectorName;
        return "(" + "oligo Sequence Fasta: " + oligoSequenceFasta + ", "
                + "Vector Name: " + vectorNameToReport + ", "
                + "Preparation Type: " + preparationTypeName + ")";
    }
}
