package ir.bontec.programmingchallenge.entities;

import ir.bontec.programmingchallenge.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = FinancialService.TABLE_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialService extends BaseEntity<Long> {
    public static final String TABLE_NAME = "service";
    private static final String NAME_SERVICE = "name_service";
    private static final String SERVICE_PRICE = "service_price";
    private static final String MAXIMUM_SERVICE_USAGE = "maximum_service_usage";
    private static final String IS_ACTIVATED = "is_activated";

    @Column(name = FinancialService.NAME_SERVICE)
    private String nameService;

    @Column(name = FinancialService.SERVICE_PRICE)
    private Double servicePrice;

    @Column(name = FinancialService.MAXIMUM_SERVICE_USAGE)
    private Long maximumServiceUsage;

    @Column(name = FinancialService.IS_ACTIVATED)
    private Boolean isActivated;

    // we define a relation many to many between FinancialService and NormalUser for get Log
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "service_normal_user",
            joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "normal_user_id", referencedColumnName = "id"))
    private Set<NormalUser> normalUsers = new HashSet<>();

}
