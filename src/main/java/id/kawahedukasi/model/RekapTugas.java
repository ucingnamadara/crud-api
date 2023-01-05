package id.kawahedukasi.model;

import io.quarkus.runtime.annotations.IgnoreProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;

@Entity
@Table(name = "rekap_tugas")
public class RekapTugas extends AuditModel {
    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", nullable = false, length = 36)
    public String id;

    @Column(name = "minggu", nullable = false)
    public Integer minggu;

    @Column(name = "nilai", nullable = false)
    public Integer nilai;

    @ManyToOne(targetEntity = Peserta.class)
    @JoinColumn(name = "peserta_id")
    public Peserta peserta;
}
