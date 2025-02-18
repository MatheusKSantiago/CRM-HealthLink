package br.edu.ifpe.CRMHealthLink.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor

public class Doctor extends User {
    public Doctor(User user) {
        this.setCpf(user.getCpf());
        this.setBirthDate(user.getBirthDate());
        this.setName(user.getUsername());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
        this.setAcessLevel(AcessLevel.DOCTOR);
    }

    @Column
    private String CRM;

    @Enumerated(EnumType.STRING)
    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="doctor_speciality",joinColumns = @JoinColumn(name="doctor_id"))
    private List<Speciality> speciality;


}
