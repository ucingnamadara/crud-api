package id.kawahedukasi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "peserta")
public class Peserta extends AuditModel {
    @Id //column id merupakan primary key
    @SequenceGenerator(
            name = "pesertaSequence",
            sequenceName = "peserta_sequence",
            initialValue = 1,
            allocationSize = 1
    )
    @GeneratedValue(generator = "pesertaSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "pob")
    public String pob;

    @Column(name = "dob")
    public LocalDate dob;

    @Column(name = "role")
    public String role;

    @Column(name = "batch")
    public Integer batch;
}
