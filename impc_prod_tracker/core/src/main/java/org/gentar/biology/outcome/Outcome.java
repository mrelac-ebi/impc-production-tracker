package org.gentar.biology.outcome;

import lombok.*;
import org.gentar.BaseEntity;
import org.gentar.biology.colony.Colony;
import org.gentar.biology.mutation.Mutation;
import org.gentar.biology.plan.Plan;
import org.gentar.biology.specimen.Specimen;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Data
@Entity
public class Outcome extends BaseEntity
{
    @Id
    @SequenceGenerator(name = "outcomeSeq", sequenceName = "OUTCOME_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outcomeSeq")
    private Long id;

    private String tpo;

    @ManyToOne
    private OutcomeType outcomeType;

    @ManyToOne
    private Plan plan;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "outcomes")
    private Set<Mutation> mutations;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(cascade=CascadeType.ALL, mappedBy = "outcome")
    private Colony colony;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(cascade=CascadeType.ALL, mappedBy = "outcome")
    private Specimen specimen;

    // Copy Constructor
    public Outcome(Outcome outcome)
    {
        this.id = outcome.id;
        this.tpo = outcome.tpo;
        this.outcomeType = outcome.outcomeType;
        this.plan = outcome.plan;
        this.mutations = new HashSet<>(outcome.mutations);
        this.colony = outcome.colony;
        this.specimen = outcome.specimen;
    }
}
