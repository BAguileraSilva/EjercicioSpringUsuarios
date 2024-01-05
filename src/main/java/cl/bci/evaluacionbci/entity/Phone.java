package cl.bci.evaluacionbci.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PHONES")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PHONE", nullable = false)
    private Long id;

    @Size(max = 4)
    @Column(name = "CITY_CODE", length = 4)
    private String cityCode;

    @Size(max = 4)
    @Column(name = "COUNTRY_CODE", length = 4)
    private String countryCode;

    @Size(max = 20)
    @Column(name = "NUMBER", length = 20)
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    private User user;

}