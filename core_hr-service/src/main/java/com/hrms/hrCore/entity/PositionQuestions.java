package com.hrms.hrCore.entity;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="position_questions", schema="core")
public class PositionQuestions {

    @Id
    @GeneratedValue
    @Column(name="id", nullable=false, updatable=false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="position_id", nullable=false, foreignKey= @ForeignKey(name="position_questions_position_id_fkey"))
    private UUID positionId;

    @Column(name="question_id", nullable=false)
    private UUID questionId;
    
    @Column(name="weighting", nullable=false, scale=2, precision=3)
    @Check(constraints = "weighting >= 0 AND weighting <= 100")
    private BigDecimal weighting;

    @CreationTimestamp
    @Column(name="created_at", nullable=false)
    private OffsetDateTime createdAt;
}
