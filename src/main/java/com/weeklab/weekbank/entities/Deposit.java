package com.weeklab.weekbank.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update tb_transactions Set deleted = true WHERE id=?")
@SQLRestriction("deleted = false")
@Table(name = "tb_transactions")
public class Deposit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;
    @Nonnull
    @ManyToOne()
    @JoinColumn(name = "payer_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private User payer;
    @Nonnull
    @ManyToOne()
    @JoinColumn(name = "payee_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private User payee;
    private int amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm'Z'")
    private LocalDateTime date;
    private String description;
    private boolean deleted;
}
